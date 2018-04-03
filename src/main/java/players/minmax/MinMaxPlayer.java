package players.minmax;

import java.awt.List;
import java.util.ArrayList;

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
	public Move getMove(GameState state) {
		TreeNode<GameState> root = this.generateFirstIndexTree(state.getLastTwoMoves());
		Move best = this.getBestMoveFromRoot(root);
		
		// The root will not have child if there is no place left to play based on the last two moves.
		// So it's necessary to analyze all the possibilities from the board;
		if(best != null){
			return best;
		}
		else {
			this.calculateNewBest(state);
			return null;
		}
	}
	
	/**
	 * 
	 * @return 
	 */
	private TreeNode<GameState> generateFirstIndexTree(ArrayList<Move> lastMoves) {

		return null;
	}

	private Move getBestMoveFromRoot(TreeNode<GameState> root){
		Move best = null;
		
		for(TreeNode<GameState> node : root.children){
			this.calculateBestMove(node);
			Move candidate = node.getTheChosenOne();
			best = this.isBest(best, candidate) ? best : candidate; 
		}
		
		return best;
	}
	
	private void calculateBestMove(TreeNode<GameState> node){
		if(!node.isLeaf()){
			this.generatePossibleMoves(node);
			for(TreeNode<GameState> child : node.children){
				this.getBestMove(child);
			}
			node.getMinMax()
		}	
		else{
			this.calculateScore(node);
			node.updateAlphaBetaFather();
		}
	}

	
}
