package com.associate.sbmfa.Fragment.AssociateManagement.Response.TreeView;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TreeViewResult {

@SerializedName("associate")
@Expose
private TreeAssociate associate;
@SerializedName("tree_data")
@Expose
private List<TreeDatum> treeData = null;

public TreeAssociate getAssociate() {
return associate;
}

public void setAssociate(TreeAssociate associate) {
this.associate = associate;
}

public List<TreeDatum> getTreeData() {
return treeData;
}

public void setTreeData(List<TreeDatum> treeData) {
this.treeData = treeData;
}

}