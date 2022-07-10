/*
 * Copyright 2022 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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

import java.security.AlgorithmConstraints
import java.util
import scala.beans.BeanProperty

class SSLParameters {

  @BeanProperty
  var algorithmConstraints: AlgorithmConstraints = null

  @BeanProperty
  var applicationProtocols: Array[String] = null

  var cipherSuites: Array[String] = null
  def getCipherSuites(): Array[String] = cipherSuites
  def setCipherSuites(cipherSuites: Array[String]): Unit =
    this.cipherSuites = cipherSuites

  @BeanProperty
  var enableRetransmissions: Boolean = true

  @BeanProperty
  var endpointIdentificationAlgorithm: String = null

  @BeanProperty
  var maximumPacketSize: Int = 0

  var needClientAuth: Boolean = false
  def getNeedClientAuth(): Boolean = needClientAuth
  def setNeedClientAuth(needClientAuth: Boolean): Unit =
    this.needClientAuth = needClientAuth

  var protocols: Array[String] = null
  def getProtocols(): Array[String] = protocols
  def setProtocols(protocols: Array[String]): Unit =
    this.protocols = protocols

  @BeanProperty
  var serverNames: util.List[SNIServerName] = null

  @BeanProperty
  var SNIMatchers: util.Collection[SNIMatcher] = null

  @BeanProperty
  var useCipherSuitesOrder: Boolean = false

  var wantClientAuth: Boolean = false
  def getWantClientAuth(): Boolean = wantClientAuth
  def setWantClientAuth(wantClientAuth: Boolean): Unit =
    this.wantClientAuth = wantClientAuth

  def this(cipherSuites: Array[String]) = {
    this()
    this.cipherSuites = cipherSuites
  }

  def this(cipherSuites: Array[String], protocols: Array[String]) = {
    this(cipherSuites)
    this.protocols = protocols
  }

}
