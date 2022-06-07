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

import java.security.Provider
import scala.beans.BeanProperty
import java.security.SecureRandom

class SSLContext(
    contextSpi: SSLContextSpi,
    @BeanProperty val provider: Provider,
    @BeanProperty val protocol: String
) {

  def createSSLEngine(): SSLEngine = contextSpi.engineCreateSSLEngine()

  def init(km: Array[KeyManager], tm: Array[TrustManager], random: SecureRandom): Unit =
    contextSpi.engineInit(km, tm, random)

}
