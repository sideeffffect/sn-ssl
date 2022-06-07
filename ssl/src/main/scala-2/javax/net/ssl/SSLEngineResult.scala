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

import scala.beans.BeanProperty

import SSLEngineResult._

class SSLEngineResult(
    @BeanProperty status: Status,
    @BeanProperty handshakeStatus: HandshakeStatus,
    val bytesConsumed: Int,
    val bytesProduced: Int,
    val sequenceNumber: Long
) {

  def this(
      status: Status,
      handshakeStatus: HandshakeStatus,
      bytesConsumed: Int,
      bytesProduced: Int
  ) = this(status, handshakeStatus, bytesConsumed, bytesProduced, -1)

}

object SSLEngineResult {

  class HandshakeStatus(name: String, ord: Int) extends Enum[HandshakeStatus](name, ord)
  object HandshakeStatus {
    val NOT_HANDSHAKING = new HandshakeStatus("NOT_HANDSHAKING", 0)
    val FINISHED = new HandshakeStatus("FINISHED", 1)
    val NEED_TASK = new HandshakeStatus("NEED_TASK", 2)
    val NEED_WRAP = new HandshakeStatus("NEED_WRAP", 3)
    val NEED_UNWRAP = new HandshakeStatus("NEED_UNWRAP", 4)
    val NEED_UNWRAP_AGAIN = new HandshakeStatus("NEED_UNWRAP_AGAIN", 5)
  }

  class Status(name: String, ord: Int) extends Enum[Status](name, ord)
  object Status {
    val BUFFER_UNDERFLOW = new Status("BUFFER_UNDERFLOW", 0)
    val BUFFER_OVERFLOW = new Status("BUFFER_OVERFLOW", 1)
    val OK = new Status("OK", 2)
    val CLOSED = new Status("CLOSED", 3)
  }

}
