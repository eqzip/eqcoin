/**
 * EQcoin core
 *
 * http://www.eqcoin.org
 *
 * @copyright 2018-present EQcoin Planet All rights reserved...
 * Copyright of all works released by EQcoin Planet or jointly released by
 * EQcoin Planet with cooperative partners are owned by EQcoin Planet
 * and entitled to protection available from copyright law by country as well as
 * international conventions.
 * Attribution — You must give appropriate credit, provide a link to the license.
 * Non Commercial — You may not use the material for commercial purposes.
 * No Derivatives — If you remix, transform, or build upon the material, you may
 * not distribute the modified material.
 * For any use of above stated content of copyright beyond the scope of fair use
 * or without prior written permission, EQcoin Planet reserves all rights to take 
 * any legal action and pursue any right or remedy available under applicable
 * law.
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
package org.eqcoin.service;

import org.apache.commons.lang3.Validate;
import org.eqcoin.keystore.Keystore;
import org.eqcoin.persistence.globalstate.GlobalState.Mode;
import org.eqcoin.persistence.globalstate.h2.GlobalStateH2;
import org.eqcoin.service.state.EQCServiceState;
import org.eqcoin.service.state.PendingTransactionState;
import org.eqcoin.stateobject.passport.EQcoinRootPassport;
import org.eqcoin.stateobject.passport.Passport;
import org.eqcoin.transaction.Transaction;
import org.eqcoin.transaction.Transaction.TransactionShape;
import org.eqcoin.util.ID;
import org.eqcoin.util.Log;
import org.eqcoin.util.Util;
import org.eqcoin.util.Value;

/**
 * @author Xun Wang
 * @date Jun 30, 2019
 * @email 10509759@qq.com
 */
public class PendingTransactionService extends EQCService {
	private static PendingTransactionService instance;
	private Value txFeeRate;
	
	private PendingTransactionService() {
		super();
		EQcoinRootPassport eQcoinRootPassport;
		try {
			eQcoinRootPassport = (EQcoinRootPassport) Util.GS().getPassport(ID.ZERO);
			txFeeRate = new Value(eQcoinRootPassport.getTxFeeRate());
		} catch (Exception e) {
			Log.Error(e.getMessage());
		}
	}
	
	public static PendingTransactionService getInstance() {
		if (instance == null) {
			synchronized (PendingTransactionService.class) {
				if (instance == null) {
					instance = new PendingTransactionService();
				}
			}
		}
		return instance;
	}
	
    /* (non-Javadoc)
	 * @see com.eqchains.service.EQCService#onDefault(com.eqchains.service.state.EQCServiceState)
	 */
	@Override
	protected synchronized void onDefault(EQCServiceState state) {
		// Here change to new method to calculate the nonce - the client should keep the nonce is continuously so if the nonce isn't correctly just discard it
		PendingTransactionState pendingTransactionState = null;
		Transaction transaction = null;
		Passport passport = null;
		try {
			Log.info("Received new Transaction");
			pendingTransactionState = (PendingTransactionState) state;
			transaction = new Transaction().setTransactionShape(TransactionShape.RPC).Parse(pendingTransactionState.getTransaction());
			transaction.setTxFeeRate(txFeeRate);
			if(!transaction.getWitness().isMeetPreCondition()) {
				Log.info("Doesn't meet pre condition just discard it");
			}
			passport = transaction.getWitness().getPassport();
			if(transaction.getNonce().compareTo(passport.getNonce()) < 0) {
				Log.info("Transaction's nonce " + transaction.getNonce() + " less than relevant Account's Asset's nonce " + passport.getNonce() + " just discard it");
				return;
			}
//			transaction.getTxIn().getLock().setId(account.getId());
//			maxNonce = EQCBlockChainH2.getInstance().getTransactionMaxNonce(transaction.getNest());
//			// Here maybe exists one bug maybe need remove this
//			if(transaction.getNonce().compareTo(maxNonce.getNonce().getNextID()) > 0) {
//				Log.info("Transaction's nonce more than relevant Account's Asset's max nonce just discard it");
//				return;
//			}
			// Here doesn't extra check to make sure nonce is continuously due to EQCBlockChainH2.getInstance().getTransactionMaxNonce may not synchronized
			
			if(!transaction.isSanity()) {
				Log.info("Transaction with ID " + passport.getId() + " isn't sanity just discard it");
				return;
			}
			
			Util.MC().saveTransactionInPool(transaction);
			Log.info("Transaction with ID " + passport.getId()  + " and nonce " + transaction.getNonce() + " is sanity just save it");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.Error(e.getMessage());
		}
	}

	public void offerPendingTransactionState(PendingTransactionState pendingTransactionState) {
		pendingMessage.offer(pendingTransactionState);
	}

	/* (non-Javadoc)
	 * @see org.eqcoin.service.EQCService#stop()
	 */
	@Override
	public synchronized void stop() {
		super.stop();
		instance = null;
	}
	
	/* (non-Javadoc)
	 * @see org.eqcoin.service.EQCService#start()
	 */
	@Override
	public synchronized void start() {
		getInstance();
		super.start();
	}
	
}
