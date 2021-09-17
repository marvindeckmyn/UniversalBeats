<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([[
            'username' => 'beetelboy',
            'name' => 'Marvin',
            'email' => 'marvin@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/xYdxkcm6iLM110kS5tF3tydm7mVh2trhIIiTzokn.png',
            'background' => 'background/Qc1O69mfP2Vq1uPw6nRl6LeX6hLPTPokuKWLNrNp.jpeg',
            'aboutme' => "My name is Marvin and I'm from Belgium.",
            'genre' => 'Hip Hop',
            'twitter' => null,
            'instagram' => 'beetelboy',
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Pierre',
            'name' => 'Pierre Bourne',
            'email' => 'pierrebourne@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/TDcBmsV3xknDrpfMpfrxqUqqTVWU6DOimQMxlHPJ.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => null,
            'genre' => null,
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'jetsonmade',
            'name' => 'Tahj Morgan',
            'email' => 'tahjmorgan@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/doC9PY9z8ug07dG4ZraLjuVnelLG7We84q5OcBG2.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "I'm from South Carolina",
            'genre' => 'Hip Hop',
            'twitter' => 'jetsonmade',
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Matthias',
            'name' => 'Matthias Lachat',
            'email' => 'matthiaslachat@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/FY480zeNxeOfZyxVyWeeJUdMnwxtb01fkxrQXrGB.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => 'Fire producer, no cap',
            'genre' => 'Hip Hop',
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Ronny',
            'name' => 'Ronny J',
            'email' => 'ronnyj@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/WH90RN57o6xKUCqmlyWWI7UPvQuW801IK68CkknF.jpeg',
            'background' => 'background/CkPAWylW2CADUvM3hZUL7Xq7m2EbfbeE8luQSdaG.jpeg',
            'aboutme' => "Ronald Oneil Spence, Jr. (born September 14, 1992), better known as his stage name Ronny J (stylized as RONNYJ) or Ronny J Listen Up.",
            'genre' => 'Hip Hop',
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Acrobatico',
            'name' => 'Lazlo',
            'email' => 'lazlo@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/NFvO5MdMX6y3hyyfDjmAwUhOY9TOCtvOPROiHY0D.jpeg',
            'background' => 'background/EAMKnQgRHpB3MhPIEYLQGtKbLjeyinI73IwCoSzT.jpeg',
            'aboutme' => null,
            'genre' => 'Hip Hop',
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Train',
            'name' => '',
            'email' => 'train@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/kLIYZJEcc4T0updoYBoZ0ChooUDJNfVSQNd0zuNU.jpeg',
            'background' => 'background/GgWKE0BnA3aDQSuIPWP9dcg7UCaaMfKp5LuqLBMQ.jpeg',
            'aboutme' => "Train is an American rock band from San Francisco, formed in 1993.",
            'genre' => 'Pop',
            'twitter' => 'train',
            'instagram' => 'train',
            'youtube' => 'train',
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'djkhaled',
            'name' => 'DJ Khaled',
            'email' => 'djkhaled@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/AidbuSythUZI15c4yuH8cBKXDguh0hgMC43W9jRP.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "The self-proclaimed King of the Anthems, DJ Khaled is an American record producer, DJ, radio personality, record label executive, actor and author.",
            'genre' => null,
            'twitter' => 'djkhaled',
            'instagram' => 'djkhaled',
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'kanye',
            'name' => 'Kanye West',
            'email' => 'kanye@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/OoD2A2P3S8DaL04CsqcgKX5KAQyYV1o5YmCKCefb.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "Kanye West is a Grammy Award-winning rapper and record producer and fashion designer.",
            'genre' => null,
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Queen',
            'name' => null,
            'email' => 'queen@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/kFgDm2F8V8spCgmbR77taCgGUoFxxd4qRvo2v1h9.png',
            'background' => 'assets/images/background.png',
            'aboutme' => "a British rock band",
            'genre' => 'Rock',
            'twitter' => 'queenwillrock',
            'instagram' => 'officialqueenmusic',
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Avicii',
            'name' => 'Tim Bergling',
            'email' => 'avicii@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/y3FfT6fk5vjNDPdlKJ7XZinPhRYpvRd9zaLNwfDW.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "a Swedish record producer and DJ",
            'genre' => 'Electronic',
            'twitter' => 'avicii',
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'BobMarley',
            'name' => 'Bob Marley',
            'email' => 'bobmarley@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/YNgqWhNNSxzEFEYgDyN8cetb73EC319F2ChKDMqL.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "A Reggae Legend",
            'genre' => 'Reggae',
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Taylor',
            'name' => 'Taylor Swift',
            'email' => 'taylorswift@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/6cYEJxS4OwmuEAAvwcB80MxDU4LCc1pdPywcRatv.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => null,
            'genre' => null,
            'twitter' => 'taylorswift13',
            'instagram' => 'taylorswift',
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Macleod',
            'name' => 'Kevin Macleod',
            'email' => 'kevinmacleod@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/G0IijdwBrN9MmuL4tRjzM8MNNLjnUJHwH68UvDQY.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => null,
            'genre' => 'World',
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'QuincyJones',
            'name' => 'Quincy Jones',
            'email' => 'quincyjones@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/vKb36C500LwEufAvPRtn9PmrCMxv6TM6IwxPMVLY.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "I'm Quincy.",
            'genre' => 'Pop',
            'twitter' => null,
            'instagram' => null,
            'youtube' => null,
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ],[
            'username' => 'Gotye',
            'name' => 'Wouter',
            'email' => 'wouterdebacker@gmail.com',
            'password' => bcrypt('useruser'),
            'avatar' => 'avatar/BPPbLwBkWsmUi8Nrwqwo0EOb8kwIY2gSfox7aCQL.jpeg',
            'background' => 'assets/images/background.png',
            'aboutme' => "I'm Gotye from Bruges.",
            'genre' => 'Pop',
            'twitter' => 'gotye',
            'instagram' => 'gotye',
            'youtube' => 'gotyemusic',
            'created_at' => Carbon::now()->format('Y-m-d H:i:s'),
            'updated_at' => Carbon::now()->format('Y-m-d H:i:s')
        ]]);
    }
}
