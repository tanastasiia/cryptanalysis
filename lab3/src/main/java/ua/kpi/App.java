package ua.kpi;

import java.math.BigInteger;

public class App {
    public static void main(String[] args) {

        var Cregular = new BigInteger("0c9abe62239493bf3c19501d9403052d4aa4594df2e7c8dbc87497dc9905677cc7a85d9b2684926432ae2552b8ece7377eb301a36d0318921b2586bad8ba77a1a9262bbe2abff74a84ff3426a19843366609933d51a7cee7410ecf442cf0c9e88eaf0e720e8488468e5f14deda84689703cbf3df41ec667c5356af76a770cfde2ff932b3a5b2ba5b789f807a4a5833c3bc5de1d2bd302f5ea1b04c62944bcc14f1b564caec0a69a4636ed8cab4cafbdd51057838d51e8f029916b1f695c07e68427c4f9a871699f8ad9d6c24b8ca940583aff9dbf9b44dae25b2fb626168e83811069552913008850bb5b29b8e30708f590dbb99e5f528ecba7e870b3d9f7c10", 16);
        var Nregular = new BigInteger("B5517805CAD74E8309CE9D0328331C52EC3BFE637235C8874C20793E708D836FACC7DBF97BAD02649EC5538A44534C589D4683BB4AAE8AD287E7E81CEEF395CD2006FF69B86E55DB97F822DC8C367066C2329150B1931F23BFD0452222214AF7835644CF9795FA094F053D51B4B49838953BBB8EADA0B607A91AE316B67981AE4AD3073541F233AB81EAC3EA9A99592318A4D19DEF536EB4FD3E7D5FEE5B6450F7A10C5D454BCDBE1B06E5F338DAD9BD183D86AC5BE473E5188D7D2554167B687B99901A036F31FF0A87A66DFF12C2CC9BDB09B20AB396E65101B9CF03844C4EC863EBBFB4CB556DDE5F0ED6BEB9EEA1F55A5F41BF55C27F8A1E6C267881E3AD", 16);
        var Lregular = 20;
        var Cdumm = new BigInteger("6ce741a2b7b181e542c99f9b061d859cffc84a5ba7355ec8ca447e7eb8911d28572cc041ec3a2c7f3e0b3c3a663c3ad45f2a07a615d06d174954ec9c3b417c9f16acca379e89534b361d1c1ffa31ac7232031d7f0005ea4354ad0b04984e7255a3b1347fb97b568624c11476449caf46a07ab2778e7cecb70b3b44848a970047", 16);
        var Ndumm = new BigInteger("BEEF432BA65E0C1AE029EF267A693A6C4FBADC88CE2AD2731C8A2E15F42DFFF9E6A770F0C4BE51FFBFAF02E53B1FD5CA16ABCF53EDC1E04E8E13DD4AA097C8BB3927E108CF9C41E77784FA5E8BD2D67FA948160B713B74F3909C8EFB1FFF1F0E12C19412B2BE885741D0A278A6B56510D7A058196BD7F7A626C371F9CE2FA117", 16);
        var Ldumm = 20;

        var Chard = new BigInteger("8bc3d0f5c307bc0826a6b772e82eceb297a130b1148935b48f8c0ea01ba3dca5b65515e060335f3dce91a016bdc2647e470c6ed39422f3e921edcb5ece243b20fac65ea36bbc56b3c82a77644b4128888011ee3adab718dbb32528b06ac27f7b02b160cdfa3ca7ffd5cbfbf8917f6941e11dbab60ebd26995f3d675ea5690c66a01a3ac4dc266c883086c32724a39bdc7f605c9aa3a36c6da9190694c0a8f85ebabd6e72afc2f9f9a6c01d50a82987b474fccdf48dca1b2fb63bd3f18f0cfd358deed021393511d1eafc7865104c83b77e52a8aeaae78ded4f23239751dd4da396543782d58c88e6be87f158b378c7f81f5472bafb673374f947c18683b790cd", 16);
        var Nhard = new BigInteger("AF9B1280D7D1700CC133731241FAA8055469E1E64634A9637274034FDF89C6BA5C0C7BFAF5E33914A45BA41F69868AED50935819B5DF3259245CC7BC81112BAA1FDC7CB9CCA4894B56584CF4E6880C49C0B46F825F6FA4565E698CD1F4070573680DC1A141EA3B22B52A46C25CA863719DB865919FABF2D90FB79986615F37D24FAAD2ADC2601C496A02ACE1DA83B217FF5207367F8AF872BB2764730B1F6C83DDC311A4244DBB8A89E5BC70558D8081A8EFA4676BAC5D2B2E7367CCEC8B2744D7D9CB843155BD1B7132139D0B7940ACF9AF1DB7E5675DDF5F805B22D0FDFD71C4CB3310487AF1E929C226C5B64C57FC8AEDCB491E6AFECA96DA5B5A26F98CC1", 16);
        var Lhard = 56;


        var e = BigInteger.valueOf(65537);
        MitM.attack(e, Chard, Nhard, Lhard);
    }

}

