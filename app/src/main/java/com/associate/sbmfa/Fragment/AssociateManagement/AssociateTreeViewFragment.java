package com.associate.sbmfa.Fragment.AssociateManagement;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.Child;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.Datum;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.TreeDatum;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.TreeListViewResponse;
import com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView.TreeViewResult;
import com.associate.sbmfa.R;
import com.associate.sbmfa.Rest.RestHandler;
import com.associate.sbmfa.Session.SessionManager;
import com.associate.sbmfa.Utils.AssociateStatusDialog;
import com.associate.sbmfa.Utils.GoogleProgressDialog;

import java.util.HashMap;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.blox.treeview.BaseTreeAdapter;
import de.blox.treeview.TreeNode;
import de.blox.treeview.TreeView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AssociateTreeViewFragment extends Fragment {
    View RootView;
    SessionManager sessionManager;
    String member_id="";
    HashMap<String ,String > UserDataToken;
    String token="";
    HashMap<String ,String > UserData;
    GoogleProgressDialog googleProgressDialog;
    ImageView imageViewBack;
    TreeNode root;
    BaseTreeAdapter<Viewholder> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_associate_tree_view, container, false);
        sessionManager = new SessionManager(getContext());
        UserDataToken =sessionManager.getUserDetailsToken();
        token=UserDataToken.get(SessionManager.KEY_TOKEN);
        UserData =sessionManager.getUserDetails();
        member_id= UserData.get(SessionManager.KEY_member_id);
        googleProgressDialog = new GoogleProgressDialog(getContext());
        if(getArguments()!=null){
            getTreeView(member_id,token,getArguments().getString("id"));
            Log.e("ididid",""+getArguments().getString("id"));
        }

        TreeView treeView = RootView.findViewById(R.id.idTreeView);


       adapter = new BaseTreeAdapter<Viewholder>(getContext(), R.layout.tree_view_node) {
            @NonNull
            @Override
            public Viewholder onCreateViewHolder(View view) {
                return new Viewholder(view);
            }
            @Override
            public void onBindViewHolder(Viewholder viewHolder, Object data, int position) {
                // inside our on bind view holder method we
                // are setting data from object to text view.
                if(data!= null) {
                    viewHolder.textView.setText(data.toString());
                }
            }
        };

        // below line is setting adapter for our tree.
        treeView.setAdapter(adapter);

        //Toast.makeText(getActivity(), getArguments().getString("id"), Toast.LENGTH_SHORT).show();







        imageViewBack = RootView.findViewById(R.id.imageView);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        RootView.setFocusableInTouchMode(true);
        RootView.requestFocus();
        RootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
        return RootView;
    }
    private void getTreeView(String member_id, String token,String id) {
        try {
             googleProgressDialog.show1("Loading data....");
            RequestBody _member_id = RequestBody.create(MediaType.parse("multipart/form-data"), member_id);
            RequestBody _token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
            RequestBody _id= RequestBody.create(MediaType.parse("multipart/form-data"), id);
            RestHandler.getApiService().ASSOCIATE_TREE_View_RESPONSE_CALL(_member_id,_token,_id).enqueue(new Callback<TreeListViewResponse>() {
          @Override
          public void onResponse(Call<TreeListViewResponse> call, Response<TreeListViewResponse> response) {
                    if (response.body()!=null){
                        if (response.body().getCode() == 200){
                                if (response.body().getAssociateStatus() == 0){
                                    AssociateStatusDialog dialog = new AssociateStatusDialog(getActivity());
                                    dialog.checkStatus();
                                }
                                googleProgressDialog.dismiss();
                                TreeViewResult treeResult = response.body().getResult();
                                List<TreeDatum>  treeData = treeResult.getTreeData();

                                root = new TreeNode(
                                          treeResult.getAssociate().getAssociateCode()+"\n"+
                                                treeResult.getAssociate().getAssociateName()+"\n"+
                                                treeResult.getAssociate().getAssociateCarder()+"\n"+
                                                treeResult.getAssociate().getStatusName()+"\n"
                                        );


                                  for (int i=0;i<treeData.size();i++){
                                    TreeNode DSAchildNode = new TreeNode(
                                              treeData.get(i).getCarderName()
                                    );

                                    root.addChild(DSAchildNode);

                                    List<Datum> datum=treeData.get(i).getData();
                                    for (int j=0;j<datum.size();j++) {
                                        final TreeNode arrayChild = new TreeNode(
                                                  datum.get(j).getAssociateCode()+"\n"+
                                                        datum.get(j).getAssociateName()+"\n"+
                                                        datum.get(j).getAssociateCarder()+"\n"+
                                                        datum.get(j).getStatusName()+"\n"
                                        );

                                    DSAchildNode.addChild(arrayChild);
                                           TreeNode childarrayChild = new TreeNode();
                                           List<Child> child = datum.get(j).getChild();
                                           if(child.size() > 0) {
                                               for (int k = 0; k < child.size(); k++) {

                                                   childarrayChild = new TreeNode(
                                                             child.get(k).getAssociateCode() + "\n" +
                                                                   child.get(k).getAssociateName() + "\n" +
                                                                   child.get(k).getAssociateCarder() + "\n" +
                                                                   child.get(k).getStatusName() + "\n"
                                                   );

                                                   arrayChild.addChild(childarrayChild);


                                        TreeNode arrayChild1 = new TreeNode();
                                        List<Child> child1 = child.get(k).getChild();
                                        if(child1.size() > 0) {
                                            for (int l = 0; l < child1.size(); l++) {

                                                arrayChild1 = new TreeNode(
                                                        child1.get(l).getAssociateCode() + "\n" +
                                                                child1.get(l).getAssociateName() + "\n" +
                                                                child1.get(l).getAssociateCarder() + "\n" +
                                                                child1.get(l).getStatusName() + "\n"
                                                );

                                                childarrayChild.addChild(arrayChild1);


                                        List<Child> child2 = child1.get(l).getChild();
                                        if(child2.size() > 0) {
                                            for (int m = 0; m < child2.size(); m++) {
                                                TreeNode arrayChild2 = new TreeNode();
                                                arrayChild2 = new TreeNode(
                                                        child2.get(m).getAssociateCode() + "\n" +
                                                                child2.get(m).getAssociateName() + "\n" +
                                                                child2.get(m).getAssociateCarder() + "\n" +
                                                                child2.get(m).getStatusName() + "\n"
                                                );

                                                arrayChild1.addChild(arrayChild2);

                                                List<Child> child3 = child2.get(m).getChild();
                                                if(child3.size() > 0) {
                                                    for (int n = 0; n < child3.size(); n++) {
                                                        TreeNode arrayChild3 = new TreeNode();
                                                        arrayChild3 = new TreeNode(
                                                                child3.get(n).getAssociateCode() + "\n" +
                                                                        child3.get(n).getAssociateName() + "\n" +
                                                                        child3.get(n).getAssociateCarder() + "\n" +
                                                                        child3.get(n).getStatusName() + "\n"
                                                        );

                                                        arrayChild2.addChild(arrayChild3);

                                                        List<Child> child4 = child3.get(n).getChild();
                                                        if(child4.size() > 0) {
                                                            for (int o = 0; o < child4.size(); o++) {
                                                                TreeNode arrayChild4 = new TreeNode();
                                                                arrayChild4 = new TreeNode(
                                                                        child4.get(o).getAssociateCode() + "\n" +
                                                                                child4.get(o).getAssociateName() + "\n" +
                                                                                child4.get(o).getAssociateCarder() + "\n" +
                                                                                child4.get(o).getStatusName() + "\n"
                                                                );

                                                                arrayChild3.addChild(arrayChild4);

                                                                List<Child> child5 = child4.get(o).getChild();
                                                                if(child5.size() > 0) {
                                                                    for (int p = 0; p < child5.size(); p++) {
                                                                        TreeNode arrayChild5 = new TreeNode();
                                                                        arrayChild5 = new TreeNode(
                                                                                child5.get(p).getAssociateCode() + "\n" +
                                                                                        child5.get(p).getAssociateName() + "\n" +
                                                                                        child5.get(p).getAssociateCarder() + "\n" +
                                                                                        child5.get(p).getStatusName() + "\n"
                                                                        );

                                                                        arrayChild4.addChild(arrayChild5);

                                                             List<Child> child6 = child5.get(p).getChild();
                                                                if(child6.size() > 0) {
                                                                    for (int q = 0; q < child6.size(); q++) {
                                                                        TreeNode arrayChild6 = new TreeNode();
                                                                        arrayChild6 = new TreeNode(
                                                                                  child6.get(q).getAssociateCode() + "\n" +
                                                                                        child6.get(q).getAssociateName() + "\n" +
                                                                                        child6.get(q).getAssociateCarder() + "\n" +
                                                                                        child6.get(q).getIsInactive() + "\n"
                                                                        );

                                                                        arrayChild5.addChild(arrayChild6);
                                                            List<Child> child7 = child6.get(q).getChild();
                                                                if(child7.size() > 0) {
                                                                    for (int r = 0; r < child7.size(); r++) {
                                                                        TreeNode arrayChild7 = new TreeNode();
                                                                        arrayChild7 = new TreeNode(
                                                                                child7.get(r).getAssociateCode() + "\n" +
                                                                                        child7.get(r).getAssociateName() + "\n" +
                                                                                        child7.get(r).getAssociateCarder() + "\n" +
                                                                                        child7.get(r).getIsInactive() + "\n"
                                                                        );

                                                                        arrayChild6.addChild(arrayChild7);
                                                             List<Child> child8 = child7.get(r).getChild();
                                                                if(child8.size() > 0) {
                                                                    for (int s = 0; s < child8.size(); r++) {
                                                                        TreeNode arrayChild8 = new TreeNode();
                                                                        arrayChild8 = new TreeNode(
                                                                                child8.get(s).getAssociateCode() + "\n" +
                                                                                        child8.get(s).getAssociateName() + "\n" +
                                                                                        child8.get(s).getAssociateCarder() + "\n" +
                                                                                        child8.get(s).getIsInactive() + "\n"
                                                                        );

                                                                        arrayChild7.addChild(arrayChild8);
//
//                                                                        List<Child> child9 = child8.get(s).getChild();
//                                                                        if(child9.size() > 0) {
//                                                                            for (int t = 0; t < child9.size(); t++) {
//                                                                                TreeNode arrayChild9 = new TreeNode();
//                                                                                arrayChild9 = new TreeNode(
//                                                                                        child9.get(t).getAssociateCode() + "\n" +
//                                                                                                child9.get(t).getAssociateName() + "\n" +
//                                                                                                child9.get(t).getAssociateCarder() + "\n" +
//                                                                                                child9.get(t).getIsInactive() + "\n"
//                                                                                );
//
//                                                                                arrayChild8.addChild(arrayChild9);
//                                                            }}
                                                                    }}}}}}}}}}}}}}}}}}
                                    }



                                }

                                adapter.setRootNode(root);

                        }else {
                            Toast.makeText(getContext(), ""+response.body().getMessages(), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        googleProgressDialog.dismiss();
                        Toast.makeText(getContext(), "Data not found.", Toast.LENGTH_SHORT).show();
                    }

                }
                @Override
                public void onFailure(Call<TreeListViewResponse> call, Throwable t) {
                    googleProgressDialog.dismiss();
                }
            });

        }catch (Exception ex){

        }
    }

//    TreeNode getNode(List<Child> child) {
//        if(child.size() > 0) {
//            for (int jj = 0; jj < child.size(); jj++) {
//                TreeNode childarrayChild = new TreeNode();
//                childarrayChild = new TreeNode(
//                        child.get(jj).getAssociateCode() + "\n" +
//                                child.get(jj).getAssociateName() + "\n" +
//                                child.get(jj).getAssociateCarder() + "\n" +
//                                child.get(jj).getIsInactive() + "\n"
//                );
//
//             mainchildarrayChild.addChild(childarrayChild);
//             List<Child> child2 = child.get(jj).getChild();
//             if(child2.size() > 0) {
//                 try {
//                     childarrayChild.addChild(getNode2(child2));
//                 }catch (Exception e){
//
//                 }
//              }
//
//            }
//
//        }
//        return mainchildarrayChild ;
//    }
//    TreeNode getNode2(List<Child> child) {
//        TreeNode mainchildarrayChild2 = new TreeNode();
//        if(child.size() > 0) {
//            for (int jj = 0; jj < child.size(); jj++) {
//                TreeNode childarrayChild = new TreeNode();
//                childarrayChild = new TreeNode(
//                        child.get(jj).getAssociateCode() + "\n" +
//                                child.get(jj).getAssociateName() + "\n" +
//                                child.get(jj).getAssociateCarder() + "\n" +
//                                child.get(jj).getIsInactive() + "\n"
//                );
//
//                mainchildarrayChild2.addChild(childarrayChild);
//                List<Child> child2 = child.get(jj).getChild();
//                if(child2.size() > 0) {
//                    try {
//                        getNode(child2);
//                    }catch (Exception e){
//
//                    }
//                }
//
//            }
//
//        }
//        return mainchildarrayChild2 ;
//    }
}