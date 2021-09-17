<?php

namespace App\Modules\Users\Services;

use Illuminate\Http\Request;

use App\Models\User;
use App\Models\Notification;
use Hash;

class UserService {

    private $userModel;
    private $notificationModel;

    public function __construct(User $userModel, Notification $notificationModel) {
        $this->userModel = $userModel;
        $this->notificationModel = $notificationModel;
    }

    public function allUsers() {
        $users = $this->userModel->orderBy('id', 'asc')->get();

        return $users;
    }

    public function allNotifications() {
        $notifications = $this->notificationModel->get();

        return $notifications;
    }

    public function updateUser($user, $data) {
        $user -> name = $data["name"];
        $user -> username = $data["username"];
        $user -> aboutme = $data["aboutme"];
        $user -> genre = $data["genre"];
        $user -> twitter = $data["twitter"];
        $user -> instagram = $data["instagram"];
        $user -> youtube = $data["youtube"];
        $user -> email = $data["email"];

        $user -> save();
    }

    public function validateUpdateUser($user) {
        $rules = [
            "name" => "string|max:30|nullable",
            "username" => "required|string|max:15|alpha_dash|unique:users,username,".$user->id,
            "aboutme" => "string|max:150|nullable",
            "genre" => "string|max:50|nullable",
            "twitter" => "string|max:255|nullable",
            "instagram" => "string|max:255|nullable",
            "youtube" => "string|max:255|nullable",
            "email" => "required|string|email|max:255|unique:users,email,".$user->id
        ];

        return $rules;
    }

    public function changeUserPassword($user, Request $request) {
        if (!Hash::check($request->get("oldpassword"), $user->password)) {
            return response()->json("Your old password has been entered incorrectly. Try again.", 401);
        }

        if (strcmp($request->get("oldpassword"), $request->get("password")) == 0) {
            return response()->json("Your old password can't be the same as your new password. Try again.", 401);
        }

        $request->validate([
            "oldpassword" => "required",
            "password" => "required|string|min:8|confirmed"
        ]);

        $user -> password = bcrypt($request -> get("password"));
        $user -> save();
    }
}