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

import org.junit.Assert._
import org.junit.Test

class SSLEngineResultTest {

  @Test
  def status() = {
    import SSLEngineResult.Status._

    assertEquals(0, BUFFER_UNDERFLOW.ordinal().toLong)
    assertEquals("BUFFER_UNDERFLOW", BUFFER_UNDERFLOW.name())

    assertEquals(1, BUFFER_OVERFLOW.ordinal().toLong)
    assertEquals("BUFFER_OVERFLOW", BUFFER_OVERFLOW.name())

    assertEquals(2, OK.ordinal().toLong)
    assertEquals("OK", OK.name())

    assertEquals(3, CLOSED.ordinal().toLong)
    assertEquals("CLOSED", CLOSED.name())
  }

}
