<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class BeatSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('beats')->insert([[
            'name' => 'Not Real',
            'producer_id' => 1,
            'cover' => 'cover/g4psghEacoZNw8Xy4aEubpd26qWDOKApbA5D4GW8.jpeg',
            'genre' => 'Hip Hop',
            'tag' => 'aesthetic',
            'bpm' => 160,
            'mood_id' => 2,
            'price' => 13,
            'audio' => 'audio/4Gst3SXtyY09j0FSu3H3SRMM2wjj4RR11qEZv1JC.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => '@ MEH',
            'producer_id' => 3,
            'cover' => 'cover/PhYivn4cxPSy11m5jvkf6AEpwqnPBbVDPuvvC23i.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 160,
            'mood_id' => 5,
            'price' => 6,
            'audio' => 'audio/lezPI52E3OQ4Echus5dZPMRspeEkbn4g3I2tNKvj.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => 'Money',
            'producer_id' => 3,
            'cover' => 'cover/0Q5VK13BGU2Ee6Naql0fOlgXqZBUVjiK88k0V17c.png',
            'genre' => 'Hip Hop',
            'tag' => 'money',
            'bpm' => 130,
            'mood_id' => 5,
            'price' => 90,
            'audio' => 'audio/nGj8F7rWNPtu74yB6Ky1fsew6PjfmEDgQTyJLWxA.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => 'Habi',
            'producer_id' => 5,
            'cover' => 'cover/Z1SpFplAbTOLNKS4KaEQN1kp7SvR5jv64iIFiGpL.jpeg',
            'genre' => 'Rock',
            'tag' => 'stomp',
            'bpm' => 110,
            'mood_id' => 8,
            'price' => 439,
            'audio' => 'audio/oUzZ96sBHAJ2BZmCHN1kGT1bqQeN7Ji98M7fZZ3T.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => 'Boinker',
            'producer_id' => 4,
            'cover' => 'cover/1A8CxUHsBCNVu8eiTT60zFnfcWB5XfxS3YaiJJDY.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 98,
            'mood_id' => 7,
            'price' => 78,
            'audio' => 'audio/dqK64MmAUIoIiJBU0rKyifviwFvjhai49ydH6HYw.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => 'Rattle',
            'producer_id' => 4,
            'cover' => 'cover/Lf2oGmx4HExpehhernjr4BvE0OeI6YYaF70G0rkV.jpeg',
            'genre' => 'R&B',
            'tag' => null,
            'bpm' => 110,
            'mood_id' => 24,
            'price' => 13,
            'audio' => 'audio/usm2IeMYWwtRUbutqQewTpr6EYMhHgslqr5rapEH.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => 'Camellia Sinensis',
            'producer_id' => 6,
            'cover' => 'cover/sMRqu58pby8rSL9MlqZ8YNwcmHEexFcdFBYljlDX.png',
            'genre' => 'Hip Hop',
            'tag' => 'Swag',
            'bpm' => 120,
            'mood_id' => 6,
            'price' => 30,
            'audio' => 'audio/cWaYjBRFhpTX56RHKoBbGI9cfaTVciduj8HtlhzY.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Hey, Soul Sister",
            'producer_id' => 7,
            'cover' => 'cover/y8bCDjuP4TyTUDVMAnrlKldBOFRZxOJOoMEy29z1.jpeg',
            'genre' => 'Pop',
            'tag' => null,
            'bpm' => 96,
            'mood_id' => 9,
            'price' => 300,
            'audio' => 'audio/VTH52teHfwHAZsHkWr9fFLRF8nnaBPCbsefnZj1U.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Gucci Flip Flops",
            'producer_id' => 5,
            'cover' => 'cover/TBwE1VV74hG1zc6Ri2iTm2rut9KP99HjEhl3wNXo.jpeg',
            'genre' => 'Hip Hop',
            'tag' => 'gucci',
            'bpm' => 80,
            'mood_id' => 21,
            'price' => 5,
            'audio' => 'audio/WbaaxLO1I1dq2bLLeza049W0hTyYx9pWxBRb2U5X.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "I Love It",
            'producer_id' => 5,
            'cover' => 'cover/GtRNWnIDOtAN3SWsbZ9xvTMIseYdFTrIp4Cx3GmV.jpeg',
            'genre' => 'Hip Hop',
            'tag' => 'love',
            'bpm' => 104,
            'mood_id' => 12,
            'price' => 45,
            'audio' => 'audio/W2Ixfk6yOPZol2OtsWoVFwANLYnerSra8cSMpkES.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Ultimate",
            'producer_id' => 5,
            'cover' => 'cover/MPxMXIKFyLBZj7wpQi8j5gPIw8MgEUqnuJ1SNByv.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 113,
            'mood_id' => 13,
            'price' => 47,
            'audio' => 'audio/bFBj9bj9ECC60qtEYWEdyiDj4quPT8YEiUjiWhQ0.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Flute",
            'producer_id' => 1,
            'cover' => 'cover/pDMzKXn2v86Mm33Ip20o20PbvwscqMF0hzFNIiXU.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 117,
            'mood_id' => 7,
            'price' => 13,
            'audio' => 'audio/8OVP8lesBHA47ayd1ouU9vG4F70x5Cv33YNJm381.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Boss",
            'producer_id' => 1,
            'cover' => 'cover/UgfgEjR7TfikX935ApGroPHv8sPr5LfwPOL0SShE.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 160,
            'mood_id' => 1,
            'price' => 13,
            'audio' => 'audio/O3U4gWFbKM5vATIBA5qfQICvFRyirV0CN41wI7vF.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "I'm The One",
            'producer_id' => 8,
            'cover' => 'cover/bzBagjMIzqtSxYlQ6pUUxHccrmilLTj8t8G0qefy.jpeg',
            'genre' => 'Pop',
            'tag' => null,
            'bpm' => 81,
            'mood_id' => 31,
            'price' => 14,
            'audio' => 'audio/c794KblcJq2DSnURBWU1BaccZpNc0KScZLssdeUF.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Wild Thoughts",
            'producer_id' => 8,
            'cover' => 'cover/t4B2rpIeePZcy0UX22lcd15USKIdc8K5VXO6Me0q.jpeg',
            'genre' => 'Pop',
            'tag' => 'number one',
            'bpm' => 98,
            'mood_id' => 35,
            'price' => 19,
            'audio' => 'audio/1sLYl8sCFecxj80z5zRUf4ceGNrc7tlXmnaU9MyF.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Greece",
            'producer_id' => 8,
            'cover' => 'cover/NRYGr7okLU0CotyWKorazAwkFy2BJpChGkIdy1nb.png',
            'genre' => 'R&B',
            'tag' => null,
            'bpm' => 86,
            'mood_id' => 26,
            'price' => 12,
            'audio' => 'audio/zPZqcyuAEbzcfElICA4E4YpXXMOqWSzNQnEROwEB.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Popstart",
            'producer_id' => 8,
            'cover' => 'cover/1dCyvQENOIzPauP8u2edivT5oySlijBXWxwbJZoG.png',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 163,
            'mood_id' => 31,
            'price' => 11,
            'audio' => 'audio/vS1wFRJcYRjZSKhYaqnZ2IrjvaypQ9Lpj01UNs6X.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Fell In Luv",
            'producer_id' => 2,
            'cover' => 'cover/uqjBIECtNxQ6ZgzyK98WYkFjHlser8Ae9fZGkf2U.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 163,
            'mood_id' => 20,
            'price' => 29,
            'audio' => 'audio/HMLI1aNUEnaQqD5vuYz7IEriWQrKGiAjvh6uLxBf.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Marie Curie",
            'producer_id' => 2,
            'cover' => 'cover/8xHW9FzBrp5gdsc9KXSlTidrjEacmkRSQ3fBEHFh.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 82,
            'mood_id' => 30,
            'price' => 25,
            'audio' => 'audio/Xb3XSgpSkEDfDmxYglLo7VrGWlklKEsXsHfICI9g.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Magnolia",
            'producer_id' => 2,
            'cover' => 'cover/Z6MrAyFqDBRZ5HY1Megu7hzqlIbAyu9DgWFJ1pZC.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 163,
            'mood_id' => 10,
            'price' => 29,
            'audio' => 'audio/Jb4cruOmtqFFHGCnaeoHE0Ev1Uboe11jmjdDpJ5j.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Kid Cudi",
            'producer_id' => 2,
            'cover' => 'cover/nJqNlpDU7s051vnjJdAEeMHFnWyytQmoUfkWnzwO.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 163,
            'mood_id' => 18,
            'price' => 39,
            'audio' => 'audio/DGGbgzoKyGA1q6WV7SzmQd95LGfqWBCkpeH08Vrp.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Playstation",
            'producer_id' => 2,
            'cover' => 'cover/LyNEVtNfMTQAlURlOXlIC1dh90hA8tQdarQj8lPh.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 162,
            'mood_id' => 19,
            'price' => 450,
            'audio' => 'audio/URggiy294aGgTuqwZRTc7k2QTaKfz7fPzHbNsfnV.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Foreign",
            'producer_id' => 2,
            'cover' => 'cover/9s6JSFoiglaFqKDqGeFn38QAeM7FpzYVGhW9pvND.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 123,
            'mood_id' => 8,
            'price' => 10,
            'audio' => 'audio/1rtVqHVD5Oo3yuJQkYaU0uIOpV49mTgq7dX5wGDa.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "dothat!",
            'producer_id' => 2,
            'cover' => 'cover/E8P5nE8Z38HzEeHUnX7PLtoFxbxNRUntCQ3oak5G.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 163,
            'mood_id' => 24,
            'price' => 10,
            'audio' => 'audio/lIi0ZjtnJkpRbug8Ui8jVuuyRT4HuGpG82gEBLFI.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "A Lot On My Mind",
            'producer_id' => 2,
            'cover' => 'cover/yETjjhw1eb0ifaGjHB99CSicOY4JLdtE4Hb6ouef.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 162,
            'mood_id' => 33,
            'price' => 11,
            'audio' => 'audio/mSyWCo6MOcJoj9YZQWpolSBYe3bmFXijfHYQH2pd.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Paris",
            'producer_id' => 9,
            'cover' => 'cover/vxutclKq2r3hcTUGf86CydpAJE2EfLGbk6aj2SRg.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 140,
            'mood_id' => 38,
            'price' => 400,
            'audio' => 'audio/bSvUXAClUAf0u7C2uQiz5WqezULlBnhhDZsnNKOB.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Father",
            'producer_id' => 9,
            'cover' => 'cover/ShyxeFrDm9tu2AnB1GPOmbDzn6ohncjVsEL4OFN5.jpeg',
            'genre' => 'R&B',
            'tag' => null,
            'bpm' => 113,
            'mood_id' => 21,
            'price' => 549,
            'audio' => 'audio/z55NU4AOpmTGQDdkg5Q0Z4bZiPsRbZalBpNyevmy.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Monster",
            'producer_id' => 9,
            'cover' => 'cover/DhqJaBGclnMqvgiqLnaB2W6g6DsTmKGRcId8nCOf.png',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 125,
            'mood_id' => 32,
            'price' => 500,
            'audio' => 'audio/GhghQAI9Wy3EqmslfCjYkZSs2D8kSxhPaZpWr8tZ.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Bound 2",
            'producer_id' => 9,
            'cover' => 'cover/HxseCQVTvlZk7GkA2IxKAjZxfoZqjeZWpLiw93YM.jpeg',
            'genre' => 'Hip Hop',
            'tag' => null,
            'bpm' => 149,
            'mood_id' => 24,
            'price' => 490,
            'audio' => 'audio/gpcX52TWFsxTS6gcG82rv09Bmj8bEv5yiOp8IKK6.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Tell Your Friends",
            'producer_id' => 9,
            'cover' => 'cover/ROl6s9mEurE8GJP57ozKXWaQ3XTY5PB0bgeY4kM1.png',
            'genre' => 'R&B',
            'tag' => null,
            'bpm' => 74,
            'mood_id' => 20,
            'price' => 540,
            'audio' => 'audio/Gj7fBn8zzBR9OM3aNN4pcdET8OkVy5CSCqsGoavB.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "I Changed My Mind",
            'producer_id' => 9,
            'cover' => 'cover/vHVPxZNOz3dbj8whO28ya330nKljbS2zchH1SY9b.jpeg',
            'genre' => 'R&B',
            'tag' => null,
            'bpm' => 94,
            'mood_id' => 21,
            'price' => 370,
            'audio' => 'audio/DMZ4P6T0P16REmYR1xMLhQN5WsWAuwwLcIpRmgyn.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Party",
            'producer_id' => 9,
            'cover' => 'cover/Izy91NWGsQVJmXiPwtPD2Q2T8TNcFA1KcR09ddWn.png',
            'genre' => 'R&B',
            'tag' => null,
            'bpm' => 80,
            'mood_id' => 25,
            'price' => 540,
            'audio' => 'audio/4258t9NgQzeV6auz3TYmkf36EDfldPOeQKePeErl.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Bohemian Rhapsody",
            'producer_id' => 10,
            'cover' => 'cover/iD3JW1fxro3aeobYoF2EwSOiSFVR0jM9eIWCAx2L.jpeg',
            'genre' => 'Rock',
            'tag' => null,
            'bpm' => 72,
            'mood_id' => 26,
            'price' => 130,
            'audio' => 'audio/ZpIFYW7NXfsihDYVyNnpkwmEWgJZ4E8sypMPuoki.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Don't Stop Me Now",
            'producer_id' => 10,
            'cover' => 'cover/cDFS1Td5bgdM7tlCEU0if6z4MKIqHdboykHB2F0R.jpeg',
            'genre' => 'Rock',
            'tag' => null,
            'bpm' => 161,
            'mood_id' => 27,
            'price' => 130,
            'audio' => 'audio/uuebwYvfMhTc6zDTbCflQSHce0vPXxas1PvsdOa6.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Under Pressure",
            'producer_id' => 10,
            'cover' => 'cover/lcNNUsrut47DTvINBBYghHcfVnoNlhWrJxJNJ64t.jpeg',
            'genre' => 'Rock',
            'tag' => null,
            'bpm' => 113,
            'mood_id' => 22,
            'price' => 100,
            'audio' => 'audio/VGvzgIsRYP0ljCN8FD1EE5SRN1shUkKPIYR9HWxY.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Black Skinhead",
            'producer_id' => 9,
            'cover' => 'cover/Js6xQ1UhBf4icnKiDROBbbqIlMxQyxKJWsPk3zJE.jpeg',
            'genre' => 'Rock',
            'tag' => null,
            'bpm' => 130,
            'mood_id' => 13,
            'price' => 670,
            'audio' => 'audio/4kQ03yExTHla7gDI2Pl7Ya2popkI3Ccj3BHy1Mhz.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Levels",
            'producer_id' => 11,
            'cover' => 'cover/rZg19YbjohCBTeTPXgqeXqllX4llgnhDsv4Cy0Rx.jpeg',
            'genre' => 'Electronic',
            'tag' => null,
            'bpm' => 127,
            'mood_id' => 12,
            'price' => 23,
            'audio' => 'audio/8mD7CeM5l1L6LRBCQodex8I1FvkWvuh8TVzZVsT2.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Jamming",
            'producer_id' => 12,
            'cover' => 'cover/83D7aBk6QPxX7CwEoxfr9YEeKR7mYXfHDRlRiqHF.jpeg',
            'genre' => 'Reggae',
            'tag' => null,
            'bpm' => 123,
            'mood_id' => 12,
            'price' => 4,
            'audio' => 'audio/9FtgTO5ApXuhrc2YOxLi4n54iNnIJehqooUmxU4w.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Lover",
            'producer_id' => 13,
            'cover' => 'cover/HXjs0V5QLHMWM71tbehAra8qQ38bH4xfRAAmDYD0.jpeg',
            'genre' => 'Country',
            'tag' => null,
            'bpm' => 69,
            'mood_id' => 15,
            'price' => 1,
            'audio' => 'audio/wIt7RSDAVHCftpqd0Ts5bry1qvFKlGSYO7Trtaef.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Medieval",
            'producer_id' => 14,
            'cover' => 'cover/4PvIJijba9EPoZIz36QCqIpTS5shf5TilMMXVrrl.jpeg',
            'genre' => 'World',
            'tag' => null,
            'bpm' => 80,
            'mood_id' => 19,
            'price' => 60,
            'audio' => 'audio/ZgH2tlp1ucsz4KFRnsee6FP49v0MgY3YlMZ9uh9e.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "slayer",
            'producer_id' => 1,
            'cover' => 'cover/UwTCILwkn5tn5z0sGW2YGtStV1zEJyv9yE3HT32n.png',
            'genre' => 'Hip Hop',
            'tag' => 'swag',
            'bpm' => 163,
            'mood_id' => 17,
            'price' => 60,
            'audio' => 'audio/8wsB53SBilehAAg4gVkMFSxJT0togD7uRPGHL6Pz.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Startin Somethin",
            'producer_id' => 15,
            'cover' => 'cover/n5EN99PchQYPv3pTIgKxTz5r5fFLm732XCsBIbce.jpeg',
            'genre' => 'Pop',
            'tag' => 'cool',
            'bpm' => 130,
            'mood_id' => 13,
            'price' => 13,
            'audio' => 'audio/JJL7ciPK9bIwl0SoWyCuukPX1E9cPTPQo1fmvTHh.mp3',
            'producer_type' => 'App\Models\User'
        ],[
            'name' => "Somebody",
            'producer_id' => 16,
            'cover' => 'cover/0F3pGoHYmOgUpmo6S0UnVyovxUHCvAWcYPuBwiFz.png',
            'genre' => 'Pop',
            'tag' => 'cool',
            'bpm' => 80,
            'mood_id' => 10,
            'price' => 30,
            'audio' => 'audio/yUbrmBzTnTZvxldlwyyJCmW3lmX1nP2FRUsnu2nf.mp3',
            'producer_type' => 'App\Models\User'
        ]]);
    }
}
