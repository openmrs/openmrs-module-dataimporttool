/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.dataimporttool.dmt.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.openmrs.module.dataimporttool.matchingschema.TupleType;

public class TupleTree {
	//to be set during translation phase
	private Object top;
	private Object curr;

	private TupleType head;

	private ArrayList<TupleTree> leafs = new ArrayList<TupleTree>();

	private TupleTree parent = null;

	private HashMap<Integer, TupleTree> locate = new HashMap<Integer, TupleTree>();

	public TupleTree() {
		// TODO Auto-generated constructor stub
	}
	
	public TupleTree(TupleType head) {
		this.head = head;
		locate.put(head.getId(), this);
	}

	public TupleTree addLeaf(TupleType leaf) {
		TupleTree t = new TupleTree(leaf);
		leafs.add(t);
		t.parent = this;
		t.locate = this.locate;
		locate.put(leaf.getId(), t);
		return t;
	}

	public TupleTree setAsParent(TupleType parentRoot) {
		TupleTree t = new TupleTree(parentRoot);
		t.leafs.add(this);
		this.parent = t;
		t.locate = this.locate;
		t.locate.put(head.getId(), this);
		t.locate.put(parentRoot.getId(), t);
		return t;
	}

	public TupleType getHead() {
		return head;
	}
	
	public void setHead(TupleType head) {
		if(this.head == null) {
			this.head = head;
			locate.put(head.getId(), this);
		}
	}

	public TupleTree getTree(Integer element) {
		return locate.get(element);
	}

	public TupleTree getParent() {
		return parent;
	}

	public Collection<TupleType> getSuccessors(Integer root) {
		Collection<TupleType> successors = new ArrayList<TupleType>();
		TupleTree tree = getTree(root);
		if (null != tree) {
			for (TupleTree leaf : tree.leafs) {
				successors.add(leaf.head);
			}
		}
		return successors;
	}

	public Collection<TupleTree> getSubTrees() {
		return leafs;
	}

	public static Collection<TupleType> getSuccessors(Integer of, Collection<TupleTree> in) {
		for (TupleTree tree : in) {
			if (tree.locate.containsKey(of)) {
				return tree.getSuccessors(of);
			}
		}
		return new ArrayList<TupleType>();
	}

	@Override
	public String toString() {
		return printTree(0);
	}

	private static final int indent = 2;

	private String printTree(int increment) {
		String s = "";
		String inc = "";
		for (int i = 0; i < increment; ++i) {
			inc = inc + " ";
		}
		s = inc + head.getId();
		for (TupleTree child : leafs) {
			s += "\n" + child.printTree(increment + indent);
		}
		return s;
	}

	public Object getTop() {
		return top;
	}

	public void setTop(Object top) {
		this.top = top;
	}

	public Object getCurr() {
		return curr;
	}

	public void setCurr(Object curr) {
		this.curr = curr;
	}
}

