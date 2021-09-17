<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Modules\Beats\Services\BeatService;
use Illuminate\Support\Facades\App;

class BeatsApiController extends Controller
{
    private $service;

    public function __construct(BeatService $service) {
        $this->service = $service;
    }

    public function beats() {
        $beats = $this->service->allBeats();

        return response()->json($beats, 200);
    }

    public function beat($id) {
        $beat = $this->service->allBeats()->find($id);

        return response()->json($beat, 200);
    }

    public function plays() {
        $plays = $this->service->allPlays();

        return response()->json($plays, 200);
    }

    public function play($id) {
        $play = $this->service->registerPlay($id);

        return response()->json($play, 200);
    }

    public function mood($language, $id) {
        $mood = $this->service->allMoods($language)->find($id);

        return response()->json($mood, 200);
    }

    public function buyBeat(Request $request) {
        $cart = $this->service->buyBeat($request);

        return response()->json($cart, 200);
    }
}
