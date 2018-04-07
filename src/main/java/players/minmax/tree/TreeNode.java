package players.minmax.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import core.Move;

@Setter
@Getter
public class TreeNode<T> implements Iterable<TreeNode<T>> {

	private T data;
	private TreeNode<T> parent;
	private List<TreeNode<T>> children;

	private int alpha;
	private int beta;
	private Move theChosenOne;

	public boolean isRoot() {
		return this.parent == null;
	}

	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	private List<TreeNode<T>> elementsIndex;

	public TreeNode(T data) {
		this.data = data;
		this.children = new LinkedList<TreeNode<T>>();
		this.elementsIndex = new LinkedList<TreeNode<T>>();
		this.elementsIndex.add(this);
	}

	public TreeNode<T> addChild(T child) {
		TreeNode<T> childNode = new TreeNode<T>(child);
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot()) {
			return 0;
		} else {
			return this.parent.getLevel() + 1;
		}
	}

	private void registerChildForSearch(TreeNode<T> node) {
		this.elementsIndex.add(node);
		if (this.parent != null) {
			this.parent.registerChildForSearch(node);
		}
	}

	public TreeNode<T> findTreeNode(Comparable<T> cmp) {
		for (TreeNode<T> element : this.elementsIndex) {
			T elData = element.data;
			if (cmp.compareTo(elData) == 0) {
				return element;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return this.data != null ? this.data.toString() : "[data null]";
	}

	@Override
	public Iterator<TreeNode<T>> iterator() {
		TreeNodeIter<T> iter = new TreeNodeIter<T>(this);
		return iter;
	}

	public Move getTheChosenOne() {
		return this.theChosenOne;

	}

}