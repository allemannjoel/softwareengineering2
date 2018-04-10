package simpleCoin.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import simpleCoin.model.payload.Message;
import simpleCoin.utility.MonitorableArrayList;

/**
 * BlockChain represents the blockchain for this currently. This is a singleton.
 */
public class BlockChain {
	public static final byte[] DIFFICULTY_1 = {0x1e, 0x0F, -1, -1}; // temporarily easier, for debugging
//	public static final byte[] DIFFICULTY_1 = {0x1d, 0x00, -1, -1}; // 0x1d00ffff same as Bitcoin
	public static final byte[] DIFFICULTY_GENISIS = {0x20, 0x0F, -1, -1}; // easier difficulty for the genesis block
	
	private MonitorableArrayList<Block> blocks = new MonitorableArrayList<>();
	private long currentDifficulty = 1;
	
	
	public BlockChain() {
	}
	
	public boolean addGenesisBlock() {
		if (blocks.size() > 0)
			return false;
		else {
			Message msg = new Message("Genesis block");
			Block blk = new Block(this);
			blk.getBlockHeader().setTargetValue(DIFFICULTY_GENISIS);
			blk.getPayload().addItem(msg);
			blk.findProofOfWork();
			blocks.add(blk);
			return true;
		}		
	}

	public Block getLastBlock() {
		if (blocks.size() > 0)
			return blocks.get(blocks.size()-1);
		else
			return null;
	}
	
	void addNewBlock(Block block) {
		if (block.isValid()) {
			blocks.add(block);
			block.setInChain();
		}
	}

	public long getCurrentDifficulty() {
		return currentDifficulty;
	}
	
	public SimpleIntegerProperty getBlockChainSizeProperty() {
		return blocks.getSizeProperty();
	}
	
	public int getBlockChainSize() {
		return blocks.size();
	}
	
	public Block getBlock(int index) {
		return blocks.get(index);
	}
}
