/*
 * Copyright 2022 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.net.ssl

import java.nio.ByteBuffer
import java.util
import java.util.function.BiFunction
import scala.beans.BeanProperty

abstract class SSLEngine(
    @BeanProperty val peerHost: String,
    @BeanProperty val peerPort: Int
) {

  def beginHandshake(): Unit

  def closeInbound(): Unit

  def closeOutbound(): Unit

  def getApplicationProtocol(): String =
    throw new UnsupportedOperationException()

  def getDelegatedTask(): Runnable

  def getEnabledCipherSuites(): Array[String]

  def getEnabledProtocols(): Array[String]

  def getEnableSessionCreation(): Boolean

  def getHandshakeApplicationProtocol(): String =
    throw new UnsupportedOperationException

  def getHandshakeApplicationProtocolSelector()
      : BiFunction[SSLEngine, util.List[String], String] =
    throw new UnsupportedOperationException

  def getHandshakeSession(): SSLSession =
    throw new UnsupportedOperationException

  def getHandshakeStatus(): SSLEngineResult.HandshakeStatus

  def getNeedClientAuth(): Boolean

  def getSession(): SSLSession

  def getSSLParameters(): SSLParameters = {
    val params = new SSLParameters
    params.setCipherSuites(getEnabledCipherSuites())
    params.setProtocols(getEnabledProtocols())
    if (getNeedClientAuth())
      params.setNeedClientAuth(true)
    else if (getWantClientAuth())
      params.setWantClientAuth(true)
    params
  }

  def getSupportedCipherSuites(): Array[String]

  def getSupportedProtocols(): Array[String]

  def getUseClientMode(): Boolean

  def getWantClientAuth(): Boolean

  def isInboundDone(): Boolean

  def isOutboundDone(): Boolean

  def setEnabledCipherSuites(suites: Array[String]): Unit

  def setEnabledProtocols(protocols: Array[String]): Unit

  def setEnableSessionCreation(flag: Boolean): Unit

  def setHandshakeAplicationProtocolSelector(
      selector: BiFunction[SSLEngine, util.List[String], String]
  ): Unit = {
    val _ = selector
    throw new UnsupportedOperationException
  }

  def setNeedClientAuth(need: Boolean): Unit

  def setSSLParameters(params: SSLParameters): Unit = {
    val cipherSuites = params.getCipherSuites()
    if (cipherSuites != null)
      setEnabledCipherSuites(cipherSuites)
    val protocols = params.getProtocols()
    if (protocols != null)
      setEnabledProtocols(protocols)
    if (params.getNeedClientAuth())
      setNeedClientAuth(true)
    else if (params.getWantClientAuth())
      setWantClientAuth(true)
    else
      setWantClientAuth(false)
  }

  def setUseClientMode(mode: Boolean): Unit

  def setWantClientAuth(want: Boolean): Unit

  def unwrap(src: ByteBuffer, dst: ByteBuffer): SSLEngineResult =
    unwrap(src, Array(dst))

  def unwrap(src: ByteBuffer, dsts: Array[ByteBuffer]): SSLEngineResult = {
    if (dsts == null) throw new IllegalArgumentException("dsts == null");
    unwrap(src, dsts, 0, dsts.length)
  }

  def unwrap(
      src: ByteBuffer,
      dsts: Array[ByteBuffer],
      offset: Int,
      length: Int
  ): SSLEngineResult

  def wrap(srcs: Array[ByteBuffer], offset: Int, length: Int, dst: ByteBuffer): SSLEngineResult

  def wrap(srcs: Array[ByteBuffer], dst: ByteBuffer): SSLEngineResult = {
    if (srcs == null) throw new IllegalArgumentException("src == null")
    wrap(srcs, 0, srcs.length, dst)
  }

  def wrap(src: ByteBuffer, dst: ByteBuffer): SSLEngineResult =
    wrap(Array(src), dst)

}
