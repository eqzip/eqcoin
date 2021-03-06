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
package org.eqcoin.keystore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.eqcoin.crypto.EQCECCPublicKey;
import org.eqcoin.keystore.Keystore.ECCTYPE;
import org.eqcoin.lock.LockTool;
import org.eqcoin.lock.LockTool.LockType;
import org.eqcoin.serialization.EQCSerializable;
import org.eqcoin.serialization.EQCCastle;
import org.eqcoin.serialization.EQCObject;
import org.eqcoin.transaction.TransferTransaction;
import org.eqcoin.transaction.Transaction.TransactionShape;
import org.eqcoin.util.Log;
import org.eqcoin.util.Util;
import org.eqcoin.util.Value;
import org.h2.engine.User;

/**
 * @author Xun Wang
 * @date 9-19-2018
 * @email 10509759@qq.com
 */
public class UserProfile extends EQCObject {

	private String userName;
	private byte[] pwdProof;
	private ECCTYPE eccType;
	private byte[] privateKey;
	private byte[] publicKey;
	private String alais;
	
	public UserProfile() {
	}

	public UserProfile(byte[] bytes) throws Exception {
		super(bytes);
	}
	
	public UserProfile(ByteArrayInputStream is) throws Exception {
		super(is);
	}
	
	/* (non-Javadoc)
	 * @see com.eqcoin.serialization.EQCSerializable#parse(java.io.ByteArrayInputStream)
	 */
	@Override
	public void parse(ByteArrayInputStream is) throws Exception {
		// Parse userName
		userName = EQCCastle.bytesToASCIISting(EQCCastle.parseBIN(is));

		// Parse pwdHash
		pwdProof = EQCCastle.parseNBytes(is, Util.SHA3_512_LEN);

		// Parse ECCTYPE
		eccType = ECCTYPE.get(EQCCastle.parseID(is).intValue());
				
		// Parse privateKey
		privateKey = EQCCastle.parseBIN(is);

		// Parse publicKey
		publicKey = EQCCastle.parseBIN(is);

		// Parse alais
		alais =  EQCCastle.bytesToASCIISting(EQCCastle.parseBIN(is));
	}
	
	/* (non-Javadoc)
	 * @see com.eqcoin.serialization.EQCSerializable#Parse(java.io.ByteArrayInputStream)
	 */
	@Override
	public UserProfile Parse(ByteArrayInputStream is) throws Exception {
		return new UserProfile(is);
	}

	@Override
	public byte[] getBytes() throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		// userName
		os.write(EQCCastle.bytesToBIN(EQCCastle.stringToASCIIBytes(userName)));
		// pwdHash
		os.write(pwdProof);
		// eccType
		os.write(eccType.getEQCBits());
		// privateKey
		os.write(EQCCastle.bytesToBIN(privateKey));
		// publicKey
		os.write(EQCCastle.bytesToBIN(publicKey));
		// alais
		os.write(EQCCastle.bytesToBIN(EQCCastle.stringToASCIIBytes(alais)));
		return os.toByteArray();
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the pwdProof
	 */
	public byte[] getPwdProof() {
		return pwdProof;
	}

	/**
	 * @param pwdProof the pwdProof to set
	 */
	public void setPwdProof(byte[] pwdProof) {
		this.pwdProof = pwdProof;
	}

	/**
	 * @return the privateKey
	 */
	public byte[] getPrivateKey() {
		return privateKey;
	}

	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}
	
	/**
	 * @return the publicKey
	 */
	public byte[] getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}
	
	/**
	 * @return the alais
	 */
	public String getAlais() {
		return alais;
	}

	/**
	 * @param alais the alais to set
	 */
	public void setAlais(String alais) {
		this.alais = alais;
	}

	/**
	 * @return the eccType
	 */
	public ECCTYPE getECCType() {
		return eccType;
	}

	/**
	 * @param eccType the eccType to set
	 */
	public void setECCType(ECCTYPE eccType) {
		this.eccType = eccType;
	}
	
	public LockType getLockType() {
		LockType lockType = null;
		if(eccType == ECCTYPE.P256) {
			lockType = LockType.T1;
		}
		else if(eccType == ECCTYPE.P521) {
			lockType = LockType.T2;
		}
		return lockType;
	}

	public boolean isPasswordCorrect(String password) throws NoSuchAlgorithmException {
		return Arrays.equals(pwdProof, MessageDigest.getInstance(Util.SHA3_512).digest(password.getBytes()));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return
				"{\n" +
				"\"UserProfile\":" + 
				"{\n" +
					"\"UserName\":" + "\"" + userName + "\"" + ",\n" +
					"\"PasswordProof\":" + "\"" + Util.dumpBytes(pwdProof, 16) + "\"" + ",\n" +
					"\"PrivateKey\":" + "\"" + Util.dumpBytes(privateKey, 16)  + "\"" + ",\n" +
					"\"PublicKey\":" + "\"" + Util.dumpBytes(publicKey, 16)  + "\"" + ",\n" +
					"\"Alais\":" + "\"" + alais  + "\"" + "\n" +
				"}\n" +
			"}";
	}

	@Override
	public boolean isSanity() throws Exception {
		if(userName == null) {
			Log.Error("userName == null");
			return false;
		}
		if(pwdProof == null) {
			Log.Error("pwdProof == null");
			return false;
		}
		if(eccType == null) {
			Log.Error("eccType == null");
			return false;
		}
		if(privateKey == null) {
			Log.Error("privateKey == null");
			return false;
		}
		if(publicKey == null) {
			Log.Error("publicKey == null");
			return false;
		}
		if(pwdProof.length != Util.SHA3_512_LEN) {
			Log.Error("pwdProof.length != Util.SHA3_512_LEN");
			return false;
		}
		if(alais == null) {
			Log.Error("alais == null");
			return false;
		}
		return true;
	}

}
