<?php

namespace in_php;

use http\Exception\RuntimeException;

class Verification {

  /**
   * @param $signedData
   * @param $signature
   * @param $public_key_res
   * @param $algorithm
   * @return bool
   */
  public static function isSignatureCorrect($signedData, $signature, $public_key_res, $algorithm): bool
  {
    $result = openssl_verify($signedData, $signature, $public_key_res, $algorithm);

    if ($result === -1) {
      $error = openssl_error_string();
      throw new RuntimeException("Verification failed with error: " . $error);
    }

    return $result === 1;
  }

}