package players.minmax;

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
		return best != null ? best : null;
	}

	/**
	 * Generate all the children of root node (actual state) based on the last two moves made ordered by score
	 *
	 * @param lastTwoMoves the last two moves made
	 * @return One root node with all children ordered by score
	 */
	private TreeNode<GameState> generateFirstIndexTree(ArrayList<Move> lastTwoMoves) {
		return null;
	}

	/**
	 * Choose one child of root to be the choosed move based on the score of each of them (Min).
	 *
	 * @param root the actual state of the game
	 * @return the move to be played or NULL if there isn't a possible single move to be made
	 */
	private Move getBestMoveFromRoot(TreeNode<GameState> root) {
		ArrayList<Move> best = null;

		for (TreeNode<GameState> node : root.children) {
			this.calculateBestMove(node);
			ArrayList<Move> candidate = node.getTheChosenOne();
			best = this.isBest(best, candidate) ? best : candidate;
		}

		return best != null ? best.get(0) : null;
	}

	/**
	 * Recursively calculate the best list of movements of the specified node along with alpha-beta prunnig.
	 * Storing it in each node.
	 *
	 * @param node the state generate the movements
	 */
	private void calculateBestMove(TreeNode<GameState> node) {
		if (!node.isLeaf()) {
			this.generatePossibleMoves(node);
			for (TreeNode<GameState> child : node.children) {
				this.calculateBestMove(child);
			}
			// node.getMinMax();
		} else {
			this.calculateScore(node);
			node.updateAlphaBetaFather();
		}
	}

	/**
	 *
	 * @param node
	 */
	private void generatePossibleMoves(TreeNode<GameState> node) {

	}

	/**
	 * Calculate the score of a specific state based on heuristic + utility function
	 *
	 * @param node
	 */
	private void calculateScore(TreeNode<GameState> node) {

	}

	/**
	 *
	 * @param best
	 * @param candidate
	 * @return
	 */
	private boolean isBest(ArrayList<Move> best, ArrayList<Move> candidate) {
		return false;
	}

}
