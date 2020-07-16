/**
 * EQcoin core - EQcoin Federation's EQcoin core library
 * @copyright 2018-present EQcoin Federation All rights reserved...
 * Copyright of all works released by EQcoin Federation or jointly released by
 * EQcoin Federation with cooperative partners are owned by EQcoin Federation
 * and entitled to protection available from copyright law by country as well as
 * international conventions.
 * Attribution — You must give appropriate credit, provide a link to the license.
 * Non Commercial — You may not use the material for commercial purposes.
 * No Derivatives — If you remix, transform, or build upon the material, you may
 * not distribute the modified material.
 * For any use of above stated content of copyright beyond the scope of fair use
 * or without prior written permission, EQcoin Federation reserves all rights to
 * take any legal action and pursue any right or remedy available under applicable
 * law.
 * https://www.eqcoin.org
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.eqcoin.persistence.globalstate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import org.eqcoin.hive.EQCHive;
import org.eqcoin.hive.EQCHiveRoot;
import org.eqcoin.lock.Lock;
import org.eqcoin.lock.LockMate;
import org.eqcoin.passport.Passport;
import org.eqcoin.seed.EQCoinSeedRoot;
import org.eqcoin.util.ID;
import org.eqcoin.util.Util;
import org.eqcoin.util.Value;

/**
 * @author Xun Wang
 * @date Oct 2, 2018
 * @email 10509759@qq.com
 */
public interface GlobalState {
	
	public class LockMateTable {
		public final static String LOCKMATE_GLOBAL = "LOCKMATE_GLOBAL";
		public final static String LOCKMATE_WALLET = "LOCKMATE_WALLET";
		public final static String ID = "id";
		public final static String TYPE = "type";
		public final static String STATUS = "status";
		public final static String PROOF = "proof";
		public final static String PUBLICKEY = "publickey";
		public final static String KEY = "key";
		public final static String LOCKMATE_SNAPSHOT = "LOCKMATE_SNAPSHOT";
		public final static String SNAPSHOT_HEIGHT = "snapshot_height";
	}
	
	public class PassportTable {
		public final static String PASSPORT_GLOBAL = "PASSPORT_GLOBAL";
		public final static String PASSPORT_WALLET = "PASSPORT_WALLET";
		public final static String ID = "id";
		public final static String LOCK_ID = "lock_id";
		public final static String TYPE = "type";
		public final static String BALANCE = "balance";
		public final static String NONCE = "nonce";
		public final static String UPDATE_HEIGHT = "update_height";
		public final static String STORAGE = "storage";
		public final static String STATE_PROOF = "state_proof";
		public final static String KEY = "key";
		public final static String PASSPORT_SNAPSHOT = "PASSPORT_SNAPSHOT";
		public final static String SNAPSHOT_HEIGHT = "snapshot_height";
	}
	
	public class EQCHiveTable {
		public final static String EQCHIVE = "EQCHIVE";
		public final static String HEIGHT = "height";
		public final static String EQCHIVE_ROOT = "eqchive_root";
		public final static String EQCOINSEED_ROOT = "eqcoinseed_root";
		public final static String EQCOINSEEDS = "eqcoinseeds";
	}
	
	public class SynchronizationTable {
		public final static String SYNCHRONIZATION = "SYNCHRONIZATION";
		public final static String TAIL_HEIGHT = "tail_height";
	}
	
	public enum Mode {
		GLOBAL, WALLET
	}
	
	public class Statistics {
		private Value totalSupply;
		private ID totalTransactionNumbers;
		/**
		 * @return the totalSupply
		 */
		public Value getTotalSupply() {
			return totalSupply;
		}
		/**
		 * @param totalSupply the totalSupply to set
		 */
		public void setTotalSupply(Value totalSupply) {
			this.totalSupply = totalSupply;
		}
		/**
		 * @return the totalTransactionNumbers
		 */
		public ID getTotalTransactionNumbers() {
			return totalTransactionNumbers;
		}
		/**
		 * @param totalTransactionNumbers the totalTransactionNumbers to set
		 */
		public void setTotalTransactionNumbers(ID totalTransactionNumbers) {
			this.totalTransactionNumbers = totalTransactionNumbers;
		}
	}
	
	public static String getLockMateTableName(Mode mode) {
		String table = null;
		if(mode == Mode.GLOBAL) {
			table = LockMateTable.LOCKMATE_GLOBAL;
		}
		else if(mode == Mode.WALLET) {
			table = LockMateTable.LOCKMATE_WALLET;
		}
		else {
			throw new IllegalStateException("Invalid Mode: " + mode);
		}
		return table;
	}
	
	public static String getPassportTableName(Mode mode) {
		String table = null;
		if(mode == Mode.GLOBAL) {
			table = PassportTable.PASSPORT_GLOBAL;
		}
		else if(mode == Mode.WALLET) {
			table = PassportTable.PASSPORT_WALLET;
		}
		else {
			throw new IllegalStateException("Invalid Mode: " + mode);
		}
		return table;
	}
	
	public Connection getConnection() throws Exception;
	
//	// Release the relevant database resource
//	public boolean close() throws Exception;

	public boolean saveLockMate(LockMate lockMate) throws Exception;

	/**
	 * Get LockMate from Global state DB according to it's ID which is the latest
	 * status.
	 * <p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public LockMate getLockMate(ID id) throws Exception;
	
//	/**
//	 * Get Lock from the specific height if which doesn't exists will return null.
//	 * If the height equal to current tail's height will retrieve the Lock from
//	 * LOCK_GLOBAL table otherwise will try retrieve it according to Lock snapshot
//	 * table to determine if it's publickey should exists.
//	 * <p>
//	 * 
//	 * @param id
//	 * @param height
//	 * @return
//	 * @throws Exception
//	 */
////	public LockMate getLockMate(ID id, ID height) throws Exception;

	public LockMate getLockMate(Lock lock) throws Exception;
	
	public boolean isLockMateExists(ID id) throws Exception;
	
	public ID isLockMateExists(Lock lock) throws Exception;

	public boolean deleteLockMate(ID id) throws Exception;

//	public boolean clearLockMate() throws Exception;

	public ID getTotalLockMateNumbers() throws Exception;
	
	public ID getTotalNewLockMateNumbers() throws Exception;
	
	public ID getLastLockMateId() throws Exception;
	
	public boolean savePassport(Passport passport) throws Exception;
	
	/**
	 * Get Passport from Global state DB according to it's ID which is the latest
	 * status.
	 * <p>
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Passport getPassport(ID id) throws Exception;

//	public byte[] getPassportBytes(ID id) throws Exception;
	
	/**
	 * Get Passport from relevant state DB according to it's lock's ID.
	 * 
	 * @param lockMateId
	 * @return Passport
	 * @throws Exception
	 */
	public Passport getPassportFromLockMateId(ID lockMateId) throws Exception;
	
	public boolean isPassportExists(ID id) throws Exception;
	
//	/**
//	 * Get passport from the specific height if which doesn't exists will return
//	 * null. If the height equal to current tail's height will retrieve the passport
//	 * from global state otherwise will try retrieve it from snapshot.
//	 * <p>
//	 * 
//	 * @param id
//	 * @param height
//	 * @return
//	 * @throws Exception
//	 */
////	public Passport getPassport(ID id, ID height) throws Exception;

	public boolean deletePassport(ID id) throws Exception;

//	public boolean clearPassport() throws Exception;
	
	public ID getTotalPassportNumbers() throws Exception;
	
	public ID getTotalNewPassportNumbers() throws Exception;
	
	public ID getLastPassportId() throws Exception;

	// relevant interface for for avro, H2(optional).
//	public boolean isEQCHiveExists(ID height) throws Exception;

//	public ID getLastEQCHiveHeight() throws Exception;
	
	public boolean saveEQCHive(EQCHive eqcHive) throws Exception;

	public byte[] getEQCHive(ID height) throws Exception;

	public EQCHiveRoot getEQCHiveRoot(ID height) throws Exception;
	
	public EQCoinSeedRoot getEQCoinSeedRoot(ID height) throws Exception;
	
	public boolean deleteEQCHive(ID height) throws Exception;
	
	public byte[] getEQCHiveRootProof(ID height) throws Exception;

	public ID getEQCHiveTailHeight() throws Exception;

	public boolean saveEQCHiveTailHeight(ID height) throws Exception;
	
	public LockMate getLockMateSnapshot(ID lockMateId, ID height) throws Exception;

	/**
	 * Save the lock's publickey update height
	 * @param lock TODO
	 * @param height
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean saveLockMateSnapshot(LockMate lockMate, ID height) throws Exception;

	public boolean deleteLockMateSnapshotFrom(ID height, boolean isForward) throws Exception;

//	/**
//	 * After verify the new block's state. Merge the new Lock states from Miner
//	 * or Valid to Global
//	 * 
//	 * @param mode
//	 * @return
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public boolean mergeLockMate() throws Exception;
	
	

//	/**
//	 * After verify the new block's state. Take the changed Lock's snapshot from
//	 * Miner or Valid.
//	 * 
//	 * @param mode
//	 * @param height TODO
//	 * @return
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public boolean takeLockMateSnapshot(ID height) throws Exception;

	/**
	 * Retrieve relevant passport's snapshot from check point height to tail
	 * height if any. If the passport's snapshot doesn't exists in snapshot which
	 * means from from check point height to tail height the passport hasn't any
	 * change. Just search in the passport snapshot table from height H to tail
	 * height if exists record which means since H the relevant passport was changed
	 * and can retrieve it's snapshot from snapshot table otherwise which means
	 * since H the passport's state which stored in the global state hasn't any
	 * change. So the passport's global state is the same as the passport in current
	 * height's state.
	 * 
	 * @param passportID
	 * @param height
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Passport getPassportSnapshot(ID passportID, ID height) throws Exception;

	public Passport getPassportSnapshotFromLockMateId(ID lockMateId, ID height) throws Exception;

	
	/**
	 * Save relevant changed passport's old state in snapshot in H height which is
	 * relevant passport's state before H. If in height H the passport was changed
	 * then the relevant passport have two states in H. One is the old state which
	 * store in the global state another is the new state which store in the
	 * mining/valid state. Before merge the new state from mining/valid to global
	 * need backup the old state in the snapshot. So when roll back to H can
	 * retrieve relevant passport in H's old state from snapshot if any.
	 * 
	 * Due to the new create passport in H only have one state so doen't need backup
	 * it's old state. If from the check point height to tail height the passport
	 * hasn't any change then which doesn't need any backup so can't find the
	 * relevant passport in snapshot.
	 * 
	 * @param passportID
	 * @param passportBytes
	 * @param height
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean savePassportSnapshot(Passport passport, ID height) throws Exception;

	public boolean deletePassportSnapshotFrom(ID height, boolean isForward) throws Exception;

//	/**
//	 * After verify the new block's state. Merge the new Passport states from Miner
//	 * or Valid to Global
//	 * 
//	 * @param mode
//	 * @return
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public boolean mergePassport() throws Exception;

//	/**
//	 * After verify the new block's state. Take the changed Passport's snapshot from
//	 * Miner or Valid.
//	 * 
//	 * @param mode
//	 * @param height TODO
//	 * @return
//	 * @throws SQLException
//	 * @throws Exception
//	 */
//	public boolean takePassportSnapshot(ID height) throws Exception;

//	// Audit layer relevant interface for H2
//	public Vector<LockMate> getForbiddenLockList() throws Exception;
	
//	public ID getTotalLivelyMasterLockNumbers
	
	public Statistics getStatistics() throws Exception;
	
}