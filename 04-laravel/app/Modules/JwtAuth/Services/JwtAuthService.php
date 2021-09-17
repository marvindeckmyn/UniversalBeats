<?php

namespace App\Modules\JwtAuth\Services;

use Illuminate\Http\Request;

use Validator;
use App\Models\User;

class JwtAuthService {

    private $userModel;

    public function __construct(User $userModel) {
        $this->userModel = $userModel;
    }

    public function validateLogin(Request $request) {
        $req = Validator::make($request->all(), [
            'username' => 'required|string|max:15',
            'password' => 'required|string|min:8',
        ]);

        return $req;
    }

    public function validateRegister(Request $request) {
        $req = Validator::make($request->all(), [
            'name' => 'string|max:30|nullable',
            'email' => 'required|string|email|max:255|unique:users,email',
            'username' => 'required|string|between:2,15|alpha_dash|unique:users,username',
            'password' => 'required|string|confirmed|min:8',
            'aboutme' => 'string|max:150|nullable',
            'genre' => 'string|max:50|nullable',
            'twitter' => 'string|max:255|nullable',
            'instagram' => 'string|max:255|nullable',
            'youtube' => 'string|max:255|nullable',
        ]);

        return $req;
    }

    public function registerUser(Request $request, $req) {
        $user = User::create(array_merge(
            $req->validated(),
            ['password' => bcrypt($request->password),
             'avatar' => "assets/images/logo.png",
             'background' => "assets/images/background.png"]
        ));
        
        return $user;
    }
}