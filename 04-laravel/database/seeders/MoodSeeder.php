<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class MoodSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('moods')->insert([[
            'id' => 1,
            'lang' => 'en',
            'name' => 'Accomplished'
        ],[
            'id' => 1,
            'lang' => 'nl',
            'name' => 'Volbracht'
        ],[
            'id' => 2,
            'lang' => 'en',
            'name' => 'Adored'
        ],[
            'id' => 2,
            'lang' => 'nl',
            'name' => 'Aanbeden'
        ],[
            'id' => 3,
            'lang' => 'en',
            'name' => 'Angry'
        ],[
            'id' => 3,
            'lang' => 'nl',
            'name' => 'Boos'
        ],[
            'id' => 4,
            'lang' => 'en',
            'name' => 'Annoyed'
        ],[
            'id' => 4,
            'lang' => 'nl',
            'name' => 'Geërgerd'
        ],[
            'id' => 5,
            'lang' => 'en',
            'name' => 'Anxious'
        ],[
            'id' => 5,
            'lang' => 'nl',
            'name' => 'Bezorgd'
        ],[
            'id' => 6,
            'lang' => 'en',
            'name' => 'Bouncy'
        ],[
            'id' => 6,
            'lang' => 'nl',
            'name' => 'Levendig'
        ],[
            'id' => 7,
            'lang' => 'en',
            'name' => 'Calm'
        ],[
            'id' => 7,
            'lang' => 'nl',
            'name' => 'Kalm'
        ],[
            'id' => 8,
            'lang' => 'en',
            'name' => 'Confident'
        ],[
            'id' => 8,
            'lang' => 'nl',
            'name' => 'Zelfverzekerd'
        ],[
            'id' => 9,
            'lang' => 'en',
            'name' => 'Crazy'
        ],[
            'id' => 9,
            'lang' => 'nl',
            'name' => 'Gek'
        ],[
            'id' => 10,
            'lang' => 'en',
            'name' => 'Crunk'
        ],[
            'id' => 10,
            'lang' => 'nl',
            'name' => 'Crunk'
        ],[
            'id' => 11,
            'lang' => 'en',
            'name' => 'Dark'
        ],[
            'id' => 11,
            'lang' => 'nl',
            'name' => 'Donker'
        ],[
            'id' => 12,
            'lang' => 'en',
            'name' => 'Depressed'
        ],[
            'id' => 12,
            'lang' => 'nl',
            'name' => 'Depressief'
        ],[
            'id' => 13,
            'lang' => 'en',
            'name' => 'Determined'
        ],[
            'id' => 13,
            'lang' => 'nl',
            'name' => 'Vastbesloten'
        ],[
            'id' => 14,
            'lang' => 'en',
            'name' => 'Dirty'
        ],[
            'id' => 14,
            'lang' => 'nl',
            'name' => 'Vuil'
        ],[
            'id' => 15,
            'lang' => 'en',
            'name' => 'Disappointed'
        ],[
            'id' => 15,
            'lang' => 'nl',
            'name' => 'Teleurgesteld'
        ],[
            'id' => 16,
            'lang' => 'en',
            'name' => 'Eccentric'
        ],[
            'id' => 16,
            'lang' => 'nl',
            'name' => 'Excentriek'
        ],[
            'id' => 17,
            'lang' => 'en',
            'name' => 'Energetic'
        ],[
            'id' => 17,
            'lang' => 'nl',
            'name' => 'Energiek'
        ],[
            'id' => 18,
            'lang' => 'en',
            'name' => 'Enraged'
        ],[
            'id' => 18,
            'lang' => 'nl',
            'name' => 'Woedend'
        ],[
            'id' => 19,
            'lang' => 'en',
            'name' => 'Epic'
        ],[
            'id' => 19,
            'lang' => 'nl',
            'name' => 'Episch'
        ],[
            'id' => 20,
            'lang' => 'en',
            'name' => 'Evil'
        ],[
            'id' => 20,
            'lang' => 'nl',
            'name' => 'Onheil'
        ],[
            'id' => 21,
            'lang' => 'en',
            'name' => 'Flirty'
        ],[
            'id' => 21,
            'lang' => 'nl',
            'name' => 'Flirterig'
        ],[
            'id' => 22,
            'lang' => 'en',
            'name' => 'Frantic'
        ],[
            'id' => 22,
            'lang' => 'nl',
            'name' => 'Waanzinnig'
        ],[
            'id' => 23,
            'lang' => 'en',
            'name' => 'Giddy'
        ],[
            'id' => 23,
            'lang' => 'nl',
            'name' => 'Duizelig'
        ],[
            'id' => 24,
            'lang' => 'en',
            'name' => 'Gloomy'
        ],[
            'id' => 24,
            'lang' => 'nl',
            'name' => 'Somber'
        ],[
            'id' => 25,
            'lang' => 'en',
            'name' => 'Grateful'
        ],[
            'id' => 25,
            'lang' => 'nl',
            'name' => 'Dankbaar'
        ],[
            'id' => 26,
            'lang' => 'en',
            'name' => 'Happy'
        ],[
            'id' => 26,
            'lang' => 'nl',
            'name' => 'Gelukkig'
        ],[
            'id' => 27,
            'lang' => 'en',
            'name' => 'Hyper'
        ],[
            'id' => 27,
            'lang' => 'nl',
            'name' => 'Hyper'
        ],[
            'id' => 28,
            'lang' => 'en',
            'name' => 'Inspiring'
        ],[
            'id' => 28,
            'lang' => 'nl',
            'name' => 'Inspirerend'
        ],[
            'id' => 29,
            'lang' => 'en',
            'name' => 'Intense'
        ],[
            'id' => 29,
            'lang' => 'nl',
            'name' => 'Intens'
        ],[
            'id' => 30,
            'lang' => 'en',
            'name' => 'Lazy'
        ],[
            'id' => 30,
            'lang' => 'nl',
            'name' => 'Lui'
        ],[
            'id' => 31,
            'lang' => 'en',
            'name' => 'Lonely'
        ],[
            'id' => 31,
            'lang' => 'nl',
            'name' => 'Eenzaam'
        ],[
            'id' => 32,
            'lang' => 'en',
            'name' => 'Loved'
        ],[
            'id' => 32,
            'lang' => 'nl',
            'name' => 'Geliefd'
        ],[
            'id' => 33,
            'lang' => 'en',
            'name' => 'Mellow'
        ],[
            'id' => 33,
            'lang' => 'nl',
            'name' => 'Zacht'
        ],[
            'id' => 34,
            'lang' => 'en',
            'name' => 'Peaceful'
        ],[
            'id' => 34,
            'lang' => 'nl',
            'name' => 'Vredig'
        ],[
            'id' => 35,
            'lang' => 'en',
            'name' => 'Rebellious'
        ],[
            'id' => 35,
            'lang' => 'nl',
            'name' => 'Opstandig'
        ],[
            'id' => 36,
            'lang' => 'en',
            'name' => 'Relaxed'
        ],[
            'id' => 36,
            'lang' => 'nl',
            'name' => 'Ontspannen'
        ],[
            'id' => 37,
            'lang' => 'en',
            'name' => 'Sad'
        ],[
            'id' => 37,
            'lang' => 'nl',
            'name' => 'Triest'
        ],[
            'id' => 38,
            'lang' => 'en',
            'name' => 'Scared'
        ],[
            'id' => 38,
            'lang' => 'nl',
            'name' => 'Bang'
        ],[
            'id' => 39,
            'lang' => 'en',
            'name' => 'Silly'
        ],[
            'id' => 39,
            'lang' => 'nl',
            'name' => 'Dwaas'
        ],[
            'id' => 40,
            'lang' => 'en',
            'name' => 'Soulful'
        ],[
            'id' => 40,
            'lang' => 'nl',
            'name' => 'Gevoelvol'
        ]]);
    }
}
