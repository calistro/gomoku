package players.minmax;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;

import core.GameInfo;
import core.GameState;
import core.Move;
import players.Player;
import players.minmax.tree.TreeNode;

public class MinMaxPlayer extends Player {

	public MinMaxPlayer(GameInfo info) {
		super(info);
	}

	@Override
	public Move getMove(GameState gameState) {
		if(this.notFistMove(gameState)){
			State state = State.clone(gameState);
			TreeNode<State> root = new TreeNode<State>(state);
			this.generateChildren(root);
			
			this.calculateMove(root);
			Move best = root.getTheChosenOne();
			
			return best;
		}
		else {
			return this.firstMove();
		}
	}
	
	private void generateChildren(TreeNode<State> node) {
		State state = node.getData();
		
		Move lastMove = state.getMoveStack().peek();
		HashSet<Move> children = state.getNearestMoves(lastMove);
		
		state.undoMove(lastMove);
		children.addAll(state.getNearestMoves(state.getMoveStack().peek()));
		
		this.calculatePartialScore();
		
		ArrayList<Move> lastTwoMoves = state.getLastTwoMoves();
	}
	
	private void calculateMove(TreeNode<State> node){
		Move best = null;
		
		for(TreeNode<State> child : node.getChildren()){
			this.calculateBestMove(child);
			Move candidate = child.getTheChosenOne();
			best = this.isBest(best, candidate) ? best : candidate; 
		}
		
		node.setTheChosenOne(best);
	}
	
	private void calculateBestMove(TreeNode<State> node){
		if(!node.isLeaf()){
			this.generateChildren(node);
			for(TreeNode<State> child : node.getChildren()){
				this.calculateMove(child);
			}
		}	
		else{
			this.calculateScore(node);
			node.updateAlphaBetaFather();
		}
	}
	
	private boolean notFistMove(GameState state){
		return state.getMoves().size() < 1;
	}
	
	private Move fistMove(){
		return null;
	}
	
	private boolean isBest(Move best, Move candidate) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
