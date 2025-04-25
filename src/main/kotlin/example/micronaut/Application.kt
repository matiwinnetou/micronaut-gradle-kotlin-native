package example.micronaut

import com.bloxbean.cardano.client.address.AddressProvider
import com.bloxbean.cardano.client.common.model.Networks
import com.bloxbean.cardano.client.crypto.KeyGenUtil
import com.bloxbean.cardano.client.crypto.SecretKey
import com.bloxbean.cardano.client.crypto.VerificationKey
import com.bloxbean.cardano.client.crypto.bip32.key.HdPublicKey

fun main(args: Array<String>) {
    val skey = args.getOrNull(0) ?: ""
    val isMainnet = args.getOrNull(1)?.toBoolean() ?: true
    if (skey.isEmpty()) {
        println("Please provide a secret key, e.g. ./cardano-cli <secretKey>")
        return
    }

    val secretKey = SecretKey(skey)
    val vk: VerificationKey = KeyGenUtil.getPublicKeyFromPrivateKey(secretKey)
    val pk: String = getAddrFromVerificationKey(vk, isMainnet)

    println("The address for secret key: $skey is $pk")
}

fun getAddrFromVerificationKey(vk: VerificationKey, isMainnet: Boolean = true): String {
    val hdPublicKey = HdPublicKey()
    hdPublicKey.setKeyData(vk.bytes)

    val network = if (isMainnet) Networks.mainnet() else Networks.testnet()
    val address = AddressProvider.getEntAddress(hdPublicKey, network)

    return address.toBech32()
}
