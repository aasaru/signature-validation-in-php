<?php

namespace in_php;

class KeyPair
{

  /**
   * @param int $keyLength
   * @return array
   */
  public static function createNewPrivateAndPublicKey(int $keyLength): array
  {
    $private_key_res = openssl_pkey_new(array(
        "private_key_bits" => ($keyLength*8),
        "private_key_type" => OPENSSL_KEYTYPE_RSA,
    ));
    $details = openssl_pkey_get_details($private_key_res);

    $public_key_string = $details['key'];
    $public_key_res = openssl_pkey_get_public($public_key_string);

    return array($private_key_res, $public_key_res);
  }

}