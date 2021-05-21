package core;

import java.util.Base64;

public class Base64EncodingDecoding {
  public static void main(String[] args) {

    System.out.println("Simple String Encode and Decoder");
    // Simple String Encoding
    String normalText = "This is Normal Text from CodersTea.com";
    Base64.Encoder simpleEncoder = Base64.getEncoder();
    String encodedText = simpleEncoder.encodeToString(normalText.getBytes());
    System.out.println(encodedText);

    // Simple Bse64 decoding
    Base64.Decoder simpleDecoder = Base64.getDecoder();
    byte[] simpleDecodedByteArray = simpleDecoder.decode(encodedText);
    String originalSimpleText = new String(simpleDecodedByteArray);
    System.out.println(originalSimpleText);

    System.out.println("URL Encode and Decoder");

    // URL encoder
    String url = "https://www.coderstea.com/tools/base-64-encode-decode.php";
    Base64.Encoder urlEncoder = Base64.getUrlEncoder();
    String encodedUrl = urlEncoder.encodeToString(url.getBytes());
    System.out.println(encodedUrl);

    // URL Decoder
    Base64.Decoder urlDecoder = Base64.getUrlDecoder();
    byte[] urlDecodedByteArray = urlDecoder.decode(encodedUrl);
    String originalUrl = new String(urlDecodedByteArray);
    System.out.println(originalUrl);

    System.out.println("MIME Friendly Base64 Encoder and Decoder");
    // MIME encoder
    String stringForMime = "This post on CodersTea.com is about " +
            "Base64 Encoding and Decoding techniques in Java " +
            "without any library";

    Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
    String encodedTextForMime = mimeEncoder.encodeToString(stringForMime.getBytes());
    System.out.println(encodedTextForMime);

    // MIME decoder
    Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
    byte[] mimeDecodedByteArray = mimeDecoder.decode(encodedTextForMime);
    String originalStringForMime = new String(mimeDecodedByteArray);
    System.out.println(originalStringForMime);
  }
}
