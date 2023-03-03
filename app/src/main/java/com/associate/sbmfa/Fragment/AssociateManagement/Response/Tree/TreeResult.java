package com.associate.sbmfa.Fragment.AssociateManagement.Response.Tree;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TreeResult {

@SerializedName("tree_list")
@Expose
private List<Tree> treeList = null;

public List<Tree> getTreeList() {
return treeList;
}

public void setTreeList(List<Tree> treeList) {
this.treeList = treeList;
}

}