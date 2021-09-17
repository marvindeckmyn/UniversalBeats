<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Modules\Users\Services\UserService;

class UsersApiController extends Controller
{
    private $service;

    public function __construct(UserService $service) {
        $this->service = $service;
    }

    public function producers() {
        $users = $this->service->allUsers();

        return response()->json($users, 200);
    }

    public function producer($id) {
        $user = $this->service->allUsers()->find($id);

        return response()->json($user, 200);
    }

    public function updateAccount(Request $request) {
        $user = auth()->user();

        $data = $this -> validateUpdateAccount($request, $user);

        $this->service->updateUser($user, $data);

        return response()->json($user, 200);
    }

    public function validateUpdateAccount(Request $request, $user) {
        $rules = $this->service->validateUpdateUser($user);

        return $request -> validate($rules);
    }

    public function changePassword(Request $request) {
        $user = auth()->user();

        $this->service->changeUserPassword($user, $request);

        return response()->json($user, 200);
    }

    public function soldBeats() {
        $notifications = $this->service->allNotifications();

        return response()->json($notifications, 200);
    }
}
