<?php

namespace in_php;

class Signing {

  public function sign($dataToSign, $private_key_res, $algorithm) {

    openssl_sign($dataToSign, $signature, $private_key_res, $algorithm);

    return $signature;
  }

}