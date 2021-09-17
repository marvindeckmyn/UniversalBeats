<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Modules\JwtAuth\Services\JwtAuthService;

class JwtAuthController extends Controller
{
    private $service;

    public function __construct(JwtAuthService $service) {
        $this->service = $service;
    }

    public function login(Request $request) {
        $req = $this->service->validateLogin($request);

        if ($req -> fails()) {
            return response()->json($req->errors(), 422);
        }

        if (! $token = auth()->attempt($req->validated())) {
            return response()->json(['Auth error' => 'Unauthorized'], 401);
        }

        return $this->generateToken($token);
    }

    public function register(Request $request) {
        $req = $this->service->validateRegister($request);

        if ($req->fails()) {
            return response()->json($req->errors()->toJson(), 400);
        }

        $user = $this->service->registerUser($request, $req);

        return response()->json([
            'message' => 'User signed up',
            'user' => $user
        ], 201);
    }

    public function logout() {
        auth()->logout();
        return response()->json(['message' => 'User logged out']);
    }

    public function refresh() {
        return $this->generateToken(auth()->refresh());
    }

    public function user() {
        return response()->json(auth()->user());
    }

    protected function generateToken($token) {
        return response()->json([
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth()->factory()->getTTL() * 60000,
            'user' => auth()->user()
        ]);
    }
}
