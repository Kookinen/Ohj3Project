/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Joni
 */
public class HelloWorld2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int i = 1000;        // int-muuttuja i = 1000.
        int j;               // int-muuttuja j = 0 (lukutyypit oletusalustuvat nollalla).

        long k = 3123456789;   // KÄÄNTÄJÄN VIRHE: 3123456789 on liian suuri ollakseen int!
        long m = 3123456789L;  // OK: 3123456789L on long-vakio, ja long kykenee esittämään sen.

        byte a = 100;        // byte-muuttuja a = 100. Int-vakio 100 --> byte-arvo 100.
        byte b = i;          // KÄÄNTÄJÄN VIRHE: arvoa muuttava muunnos int --> byte.
                              // Java muuntaa int-vakion pienempään kokonaislukutyyppiin, JOS
                              // arvon voi esittää pienemmälläkin tyypillä. i = 1000 ei mahdu
                              // byteen vaan muuntuisi byte-arvoksi -24.
        byte c = (byte) i;   // OK: pakotettu tyyppimuunnos int --> byte ja tuloksena c = -24.
                              // Javan tyyppimuunnoksen syntaksi on suoraan C-kielestä:
                              // annetaan kohdetyyppi suluissa muunnettavan arvon edessä.

        boolean e;                // boolean-muuttuja e = false (oletusalustuu epätodeksi).
        boolean f = true;         // boolean-muuttuja f = true.
        boolean g = (boolean) i;  // KÄÄNTÄJÄN VIRHE: int-arvoa i ei voi muuntaa totuusarvoksi.
                                  // Toisin kuin C/C++/Pythonissa, Javassa totuusarvoja ei voi
                                  // muuntaa lukuarvoiksi!
        boolean h = i > j;        // h = true, koska ehtolauseen i > j eli 1000 > 0 arvo on true.

        float x = 0.0;       // KÄÄNTÄJÄN VIRHE: arvo 0.0 on double-vakio, eikä Java muunna
                              // double-arvoa float-arvoksi ilman erillistä tyyppimuunnosta.

        float y = 1.5F;      // OK: 1.5F on float-vakio.
        float z = 0;         // OK: implisiittinen muunnos int 0 --> float 0.0F.

        double p = 3.14;
        float q = p;           // KÄÄNTÄJÄN VIRHE: double-arvon muunnos float-arvoksi!
        float r = (float) p;  // OK: pakotettu tyyppimuunnos double --> float.

        int s = p;           // KÄÄNTÄJÄN VIRHE: double-arvon muunnos int-arvoksi!
        int t = y;           // KÄÄNTÄJÄN VIRHE: float-arvon muunnos int-arvoksi!
        int u = (int) y;     // OK: pakotettu tyyppimuunnos float --> int. Tuloksena u = 1 eli
                              // arvo y = 1.5 ilman desimaaliosaa (ei hienoa pyöristystä tms.).
    }
}
