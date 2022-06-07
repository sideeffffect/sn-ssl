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

  @BeanProperty
  var cipherSuites: Array[String] = null

  @BeanProperty
  var enableRetransmissions: Boolean = true

  @BeanProperty
  var endpointIdentificationAlgorithm: String = null

  @BeanProperty
  var maximumPacketSize: Int = 0

  @BeanProperty
  var needClientAuth: Boolean = false

  @BeanProperty
  var protocols: Array[String] = null

  @BeanProperty
  var serverNames: util.List[SNIServerName] = null

  @BeanProperty
  var SNIMatchers: util.Collection[SNIMatcher] = null

  @BeanProperty
  var useCipherSuitesOrder: Boolean = false

  @BeanProperty
  var wantClientAuth: Boolean = false

  def this(cipherSuites: Array[String]) = {
    this()
    this.cipherSuites = cipherSuites
  }

  def this(cipherSuites: Array[String], protocols: Array[String]) = {
    this(cipherSuites)
    this.protocols = protocols
  }

}
