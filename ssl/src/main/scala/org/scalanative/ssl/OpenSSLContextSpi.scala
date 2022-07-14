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

package org.scalanative.ssl

import org.scalanative.openssl

import java.security.SecureRandom
import java.util.Collections
import java.util.IdentityHashMap
import java.util.Set
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLContextSpi
import javax.net.ssl.SSLEngine
import javax.net.ssl.SSLServerSocketFactory
import javax.net.ssl.SSLSessionContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import scala.scalanative.unsafe

class OpenSSLContextSpi(ctx: unsafe.Ptr[openssl.ssl.SSL_CTX])
    extends SSLContextSpi
    with AutoCloseable {

  private[this] val gcRoot: Set[Any] = Collections.newSetFromMap(new IdentityHashMap)

  def this() = this(openssl.ssl.SSL_CTX_new(openssl.ssl.TLS_method()))

  def engineInit(km: Array[KeyManager], tm: Array[TrustManager], random: SecureRandom): Unit = {
    Option(tm).flatMap(_.collectFirst { case x509tm: X509TrustManager => x509tm }).foreach {
      _ =>
        val cb = { (_: unsafe.Ptr[openssl.types.X509_STORE_CTX], _: unsafe.Ptr[Unit]) =>
          0: unsafe.CInt // TODO
        }

        openssl.ssl.SSL_CTX_set_cert_verify_callback(ctx, cb, null)

        gcRoot.add(cb)
    }

  }

  def engineGetSocketFactory(): SSLSocketFactory = ???

  def engineGetServerSocketFactory(): SSLServerSocketFactory = ???

  def engineCreateSSLEngine(): SSLEngine = ???

  def engineCreateSSLEngine(host: String, port: Int): SSLEngine = ???

  def engineGetServerSessionContext(): SSLSessionContext = ???

  def engineGetClientSessionContext(): SSLSessionContext = ???

  def close(): Unit = openssl.ssl.SSL_CTX_free(ctx)

}
