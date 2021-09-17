<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBeatsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('beats', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->integer('producer_id');
            $table->string('cover');
            $table->string('genre');
            $table->string('tag')->nullable();
            $table->string('bpm');
            $table->integer('mood_id');
            $table->integer('price');
            $table->string('audio');
            $table->string('producer_type');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('beats');
    }
}
