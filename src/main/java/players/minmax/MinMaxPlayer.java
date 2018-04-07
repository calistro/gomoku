package players.minmax;

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
		if (this.notFistMove(gameState)) {
			State state = State.clone(gameState);
			TreeNode<State> root = new TreeNode<State>(state);
			this.generateChildren(root);

			this.calculateMove(root);
			Move best = root.getTheChosenOne();

			return best;
		} else {
			return this.firstMove();
		}
	}

	private void generateChildren(TreeNode<State> node) {
		State state = node.getData();

		// Add all the nearest moves based on the last two moves and if there is no Move possible to be made, is added all the others possibilities
		Move lastMove = state.getMoveStack().peek();
		HashSet<Move> moves = state.getNearestMoves(lastMove);

		state.undoMove(lastMove);
		moves.addAll(state.getNearestMoves(state.getMoveStack().peek()));
		moves.remove(lastMove); // If the last move made was one of the nearest, need to be removed

		if (moves.isEmpty()) {
			moves.addAll(state.getAllPossibleMoves());
		}

		for (Move move : moves) {
			node.addChild(this.calculatePartialScore(state, move));
		}

	}

	private void calculateMove(TreeNode<State> node) {
		Move best = null;

		for (TreeNode<State> child : node.getChildren()) {
			this.calculateBestMove(child);
			Move candidate = child.getTheChosenOne();
			best = this.isBest(best, candidate) ? best : candidate;
		}

		node.setTheChosenOne(best);
	}

	private void calculateBestMove(TreeNode<State> node) {
		if (!node.isLeaf()) {
			this.generateChildren(node);
			for (TreeNode<State> child : node.getChildren()) {
				this.calculateMove(child);
			}
		} else {
			this.calculateScore(node);
			node.updateAlphaBetaFather();
		}
	}

	private boolean notFistMove(GameState state) {
		return state.getMoves().size() < 1;
	}

	private Move firstMove() {
		Move move = new Move();

		return move;
	}

	private boolean isBest(Move best, Move candidate) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Calculate the score of a specific state based on heuristic + utility function
	 *
	 * @param node
	 */
	private void calculateScore(TreeNode<GameState> node) {

	}

}
