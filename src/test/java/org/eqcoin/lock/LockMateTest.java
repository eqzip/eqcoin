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
package org.eqcoin.lock;

import static org.junit.jupiter.api.Assertions.*;

import org.eqcoin.lock.LockMate.STATUS;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Xun Wang
 * @date May 29, 2020
 * @email 10509759@qq.com
 */
class LockMateTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#getHeaderBytes(java.io.ByteArrayOutputStream)}.
	 */
	@Test
	final void testGetHeaderBytes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#getBodyBytes(java.io.ByteArrayOutputStream)}.
	 */
	@Test
	final void testGetBodyBytes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#isSanity()}.
	 */
	@Test
	final void testIsSanity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#init()}.
	 */
	@Test
	final void testInit() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#toString()}.
	 */
	@Test
	final void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#toInnerJson()}.
	 */
	@Test
	final void testToInnerJson() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#LockMate()}.
	 */
	@Test
	final void testLockMate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#getId()}.
	 */
	@Test
	final void testGetId() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#setId(org.eqcoin.util.ID)}.
	 */
	@Test
	final void testSetId() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#getLock()}.
	 */
	@Test
	final void testGetLock() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#setLock(org.eqcoin.lock.Lock)}.
	 */
	@Test
	final void testSetLock() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#getPublickey()}.
	 */
	@Test
	final void testGetPublickey() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#setPublickey(org.eqcoin.lock.Publickey)}.
	 */
	@Test
	final void testSetPublickey() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#setChangeLog(org.eqcoin.changelog.ChangeLog)}.
	 */
	@Test
	final void testSetChangeLog() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#planting()}.
	 */
	@Test
	final void testPlanting() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#setStatus(org.eqcoin.lock.LockMate.STATUS[])}.
	 */
	@Test
	final void testSetStatus() {
		LockMate lockMate = new LockMate();
		assertFalse(lockMate.isMaster());
		assertFalse(lockMate.isSub());
		assertFalse(lockMate.isLively());
		assertFalse(lockMate.isForbidden());
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#isMaster()}.
	 */
	@Test
	final void testIsMaster() {
		LockMate lockMate = new LockMate();
		assertTrue(lockMate.isMaster());
		lockMate.setSub();
		assertTrue(lockMate.isSub());
		assertFalse(lockMate.isMaster());
		lockMate.setMaster();
		assertTrue(lockMate.isMaster());
		assertFalse(lockMate.isSub());
		assertTrue(lockMate.isLively());
		assertFalse(lockMate.isForbidden());
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#isSub()}.
	 */
	@Test
	final void testIsSub() {
		LockMate lockMate = new LockMate();
		assertFalse(lockMate.isSub());
		lockMate.setSub();
		assertTrue(lockMate.isSub());
		lockMate.setMaster();
		assertFalse(lockMate.isSub());
		assertTrue(lockMate.isMaster());
		assertTrue(lockMate.isLively());
		assertFalse(lockMate.isForbidden());
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#isLively()}.
	 */
	@Test
	final void testIsLively() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.lock.LockMate#isForbidden()}.
	 */
	@Test
	final void testIsForbidden() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#EQCSerializable()}.
	 */
	@Test
	final void testEQCSerializable() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#EQCSerializable(byte[])}.
	 */
	@Test
	final void testEQCSerializableByteArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#EQCSerializable(java.io.ByteArrayInputStream)}.
	 */
	@Test
	final void testEQCSerializableByteArrayInputStream() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#parse(java.io.ByteArrayInputStream)}.
	 */
	@Test
	final void testParse() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#Parse(java.io.ByteArrayInputStream)}.
	 */
	@Test
	final void testParseByteArrayInputStream() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#Parse(byte[])}.
	 */
	@Test
	final void testParseByteArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#parseHeader(java.io.ByteArrayInputStream)}.
	 */
	@Test
	final void testParseHeader() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#parseBody(java.io.ByteArrayInputStream)}.
	 */
	@Test
	final void testParseBody() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#getBytes(java.io.ByteArrayOutputStream)}.
	 */
	@Test
	final void testGetBytesByteArrayOutputStream() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#getBytes()}.
	 */
	@Test
	final void testGetBytes() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#getBin()}.
	 */
	@Test
	final void testGetBin() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link org.eqcoin.serialization.EQCSerializable#isValid()}.
	 */
	@Test
	final void testIsValid() {
		fail("Not yet implemented"); // TODO
	}

}
