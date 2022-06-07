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

class SSLEngineResult {}

object SSLEngineResult {

  class Status(name: String, ord: Int) extends Enum[Status](name, ord)
  object Status {
    val BUFFER_UNDERFLOW = new Status("BUFFER_UNDERFLOW", 0)
    val BUFFER_OVERFLOW = new Status("BUFFER_OVERFLOW", 1)
    val OK = new Status("OK", 2)
    val CLOSED = new Status("CLOSED", 3)
  }

}
