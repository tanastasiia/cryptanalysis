package ua.kpi;

import java.math.BigInteger;
import java.util.List;

public class App {

    public static void mitMAttack() {

        var Cdumm = new BigInteger("6ce741a2b7b181e542c99f9b061d859cffc84a5ba7355ec8ca447e7eb8911d28572cc041ec3a2c7f3e0b3c3a663c3ad45f2a07a615d06d174954ec9c3b417c9f16acca379e89534b361d1c1ffa31ac7232031d7f0005ea4354ad0b04984e7255a3b1347fb97b568624c11476449caf46a07ab2778e7cecb70b3b44848a970047", 16);
        var Ndumm = new BigInteger("BEEF432BA65E0C1AE029EF267A693A6C4FBADC88CE2AD2731C8A2E15F42DFFF9E6A770F0C4BE51FFBFAF02E53B1FD5CA16ABCF53EDC1E04E8E13DD4AA097C8BB3927E108CF9C41E77784FA5E8BD2D67FA948160B713B74F3909C8EFB1FFF1F0E12C19412B2BE885741D0A278A6B56510D7A058196BD7F7A626C371F9CE2FA117", 16);
        var Ldumm = 20;

        var Cregular = new BigInteger("0c9abe62239493bf3c19501d9403052d4aa4594df2e7c8dbc87497dc9905677cc7a85d9b2684926432ae2552b8ece7377eb301a36d0318921b2586bad8ba77a1a9262bbe2abff74a84ff3426a19843366609933d51a7cee7410ecf442cf0c9e88eaf0e720e8488468e5f14deda84689703cbf3df41ec667c5356af76a770cfde2ff932b3a5b2ba5b789f807a4a5833c3bc5de1d2bd302f5ea1b04c62944bcc14f1b564caec0a69a4636ed8cab4cafbdd51057838d51e8f029916b1f695c07e68427c4f9a871699f8ad9d6c24b8ca940583aff9dbf9b44dae25b2fb626168e83811069552913008850bb5b29b8e30708f590dbb99e5f528ecba7e870b3d9f7c10", 16);
        var Nregular = new BigInteger("B5517805CAD74E8309CE9D0328331C52EC3BFE637235C8874C20793E708D836FACC7DBF97BAD02649EC5538A44534C589D4683BB4AAE8AD287E7E81CEEF395CD2006FF69B86E55DB97F822DC8C367066C2329150B1931F23BFD0452222214AF7835644CF9795FA094F053D51B4B49838953BBB8EADA0B607A91AE316B67981AE4AD3073541F233AB81EAC3EA9A99592318A4D19DEF536EB4FD3E7D5FEE5B6450F7A10C5D454BCDBE1B06E5F338DAD9BD183D86AC5BE473E5188D7D2554167B687B99901A036F31FF0A87A66DFF12C2CC9BDB09B20AB396E65101B9CF03844C4EC863EBBFB4CB556DDE5F0ED6BEB9EEA1F55A5F41BF55C27F8A1E6C267881E3AD", 16);
        var Lregular = 20;

        var Chard = new BigInteger("8bc3d0f5c307bc0826a6b772e82eceb297a130b1148935b48f8c0ea01ba3dca5b65515e060335f3dce91a016bdc2647e470c6ed39422f3e921edcb5ece243b20fac65ea36bbc56b3c82a77644b4128888011ee3adab718dbb32528b06ac27f7b02b160cdfa3ca7ffd5cbfbf8917f6941e11dbab60ebd26995f3d675ea5690c66a01a3ac4dc266c883086c32724a39bdc7f605c9aa3a36c6da9190694c0a8f85ebabd6e72afc2f9f9a6c01d50a82987b474fccdf48dca1b2fb63bd3f18f0cfd358deed021393511d1eafc7865104c83b77e52a8aeaae78ded4f23239751dd4da396543782d58c88e6be87f158b378c7f81f5472bafb673374f947c18683b790cd", 16);
        var Nhard = new BigInteger("AF9B1280D7D1700CC133731241FAA8055469E1E64634A9637274034FDF89C6BA5C0C7BFAF5E33914A45BA41F69868AED50935819B5DF3259245CC7BC81112BAA1FDC7CB9CCA4894B56584CF4E6880C49C0B46F825F6FA4565E698CD1F4070573680DC1A141EA3B22B52A46C25CA863719DB865919FABF2D90FB79986615F37D24FAAD2ADC2601C496A02ACE1DA83B217FF5207367F8AF872BB2764730B1F6C83DDC311A4244DBB8A89E5BC70558D8081A8EFA4676BAC5D2B2E7367CCEC8B2744D7D9CB843155BD1B7132139D0B7940ACF9AF1DB7E5675DDF5F805B22D0FDFD71C4CB3310487AF1E929C226C5B64C57FC8AEDCB491E6AFECA96DA5B5A26F98CC1", 16);
        var Lhard = 56;


        var e = BigInteger.valueOf(65537);

        MitM.attack(e, Cregular, Nregular, Lregular);
        // MitM.attack(e, Chard, Nhard, Lhard);

    }

    public static void seAttack() {


        var Csdummdumm = List.of(
                new BigInteger("5107658823a908152cac8bfc8a0452850576c691082053ca32edbe4dc6e78b61", 16),
                new BigInteger("bc296c2f46c9935b31824d1ff1588ec577504fab36817e193c45bdc640fe6337", 16),
                new BigInteger("b473aca2912a38d8d5e484361d99470dc20ace3aa540b115683e0e72001606c6", 16)
        );

        var Nsdummdumm = List.of(
                new BigInteger("ADE5E2780C281D597E986F0554CA88A3A42777F12DEBC87F9A150E4CA2196DAB", 16),
                new BigInteger("F666AA15FB5F4FA2D197ACA1D98FDA9A07E1442155B3AF8B5BF76FEC27D8F807", 16),
                new BigInteger("BBCB9987EB9074BA23A6F18A6160F855D2194B228950DC8DB4FD820AE5526479", 16)
        );
        var eDummdumm = 3;

        //512_5
        var Csreg = List.of(
                new BigInteger("15b92b6fd6eec5bdbbc3f8f17b8fe60a7bc59d1afe9b09f9bf5cd7cc4229f5b3d862e63874158c04577f5eddb895a45b89e0398a5d2674d0dea3af4c723dab10", 16),
                new BigInteger("2346b6351cd7abb796a40aab9a0026814bac56a4a9f9fa26cbd7be3a5b7883a249cf15caa580f2487c6d8751daf63b513cab546bf00e26e6e28b90acef10ae43", 16),
                new BigInteger("46a724c3564fe91c7b7085860a553e30f88a896d7d2a189d1a9ab3eb49a2e41a06a4dc8ed2ead01658bc9f62157fab62a92de51b6fd7d1516a53c9359aa3b80c", 16),
                new BigInteger("0e60f9fe42c518e14f59402ec7bbc699ac1b069890670fd6c3b5d12ea1cf66ff5c78410ff4a4610afc4942787e2833866c5c76cdff3d14b2753f7ab9cf25efe0", 16),
                new BigInteger("be58517263de81bb2421eb6270ae50bd658be07a2cf59f57c9df1a3b7138fc92f75bbb5fd11a008f3889ba8e9dad15145f3a5536a274470b4a8a2b15945bcc1b", 16)
        );

        var Nsreg = List.of(
                new BigInteger("978ED705B5E0823415957EBBD7EE3AFDAB24B4F2ED6DA5D3B9910890CAD81D0D237C7AA6A1209456F3A03E56BBBB156A0B2A6B233AD1A0FB41E5261BE800FEF3", 16),
                new BigInteger("D00B1F04B40E670DE1292496637873864F7334FCFC89FFFC0291337CD8ADD52C7BD9D1E2DD5D6B51ABB525AF05D692F169E6956F5491D9A1F7CA816FFB4ECFA1", 16),
                new BigInteger("AA90B514994B040E4DD5899806A5A7D919C0B305F46903A3373BCA173585953D5AFFED4ABAD37E0A74316D1436DF8F36D5C2FB6CED0C06F8BD3633FC187928A3", 16),
                new BigInteger("BDED7993ED260C686D29D725FA107A27979F1D5EABFFAA5A6417E3DA8AFFD8A5DCF8E49F69B161EE8DE9FE7DEE81242DEB1A59C5CB758253D2B1FDD8DA99D7D9", 16),
                new BigInteger("C80634F14FB8E7A18082BE80D87033B11748DD348CF7A657D30CB06991E617610BCB2E1CE90B613BC41E9969BA2323DAA54FEC4002CB85885ED672777F3137B7", 16)
        );

        var eReg = 5;

        Se.attack(Csreg, Nsreg, eReg);

    }

    public static void main(String[] args) {

        System.out.println("SE ATTACK: ");
        seAttack();
        System.out.println("\nMitM ATTACK: ");
        mitMAttack();

    }

}

