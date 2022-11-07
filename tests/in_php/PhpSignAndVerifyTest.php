<?php

namespace in_php;

use PHPUnit\Framework\TestCase;

class PhpSignAndVerifyTest extends TestCase {


  public function testsignAndVerify() {

    $signing = new Signing();

    list($private_key_res, $public_key_res) = KeyPair::createNewPrivateAndPublicKey(512);

    $dataToSign = 'my data to sign';

    $signature = $signing->sign($dataToSign, $private_key_res, "sha512WithRSAEncryption");

    self::assertThat(strlen($signature), self::equalTo(512));

    $verificationResult = Verification::isSignatureCorrect($dataToSign, $signature, $public_key_res, OPENSSL_ALGO_SHA512);

    self::assertTrue($verificationResult);
  }

}
