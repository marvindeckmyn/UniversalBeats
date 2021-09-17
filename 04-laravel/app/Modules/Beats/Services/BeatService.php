<?php

namespace App\Modules\Beats\Services;

use Illuminate\Http\Request;

use App\Models\Beat;
use App\Models\Mood;
use App\Models\Play;
use App\Notifications\SoldBeat;
use App\Notifications\PushSoldBeat;

class BeatService {
    
    private $beatModel;
    private $moodModel;
    private $playModel;
    
    public function __construct(Beat $beatModel, Mood $moodModel, Play $playModel) {
        $this->beatModel = $beatModel;
        $this->moodModel = $moodModel;
        $this->playModel = $playModel;
    }

    public function allBeats() {
        $beats = $this->beatModel->get();

        return $beats;
    }

    public function allMoods($lang) {
        $moods = $this->moodModel->where("lang", $lang)->get();

        return $moods;
    }

    public function allPlays() {
        $plays = $this->playModel->get();

        return $plays;
    }

    public function registerPlay($id) {
        $play = Play::create([
            'beat_id' => $id
        ]);

        return $play;
    }

    public function buyBeat(Request $request) {
        $cart = $request -> get("cart");

        foreach ($cart as $beatId) {
            $beat = Beat::find($beatId);
            $producer = $beat->producer;
            $producer->notify(new SoldBeat($beat));
            $producer->notify(new PushSoldBeat($beat));
        }

        return $cart;
    }
}