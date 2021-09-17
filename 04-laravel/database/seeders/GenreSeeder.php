<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class GenreSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('genres')->insert([[
            'name' => 'Hip Hop'
        ],[
            'name' => 'Pop'
        ],[
            'name' => 'R&B'
        ],[
            'name' => 'Rock'
        ],[
            'name' => 'Electronic'
        ],[
            'name' => 'Reggae'
        ],[
            'name' => 'Country'
        ],[
            'name' => 'World'
        ]]);
    }
}
