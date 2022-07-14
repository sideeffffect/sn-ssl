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

package org.scalanative.openssl

import scala.annotation.nowarn
import scala.scalanative.unsafe._

@link("ssl")
@extern
@nowarn
object ssl {

  type SSL_CTX
  type SSL_METHOD

  def SSL_CTX_new(meth: Ptr[SSL_METHOD]): Ptr[SSL_CTX] = extern

  def SSL_CTX_free(ctx: Ptr[SSL_CTX]): Unit = extern

  def TLS_method(): Ptr[SSL_METHOD] = extern

}
