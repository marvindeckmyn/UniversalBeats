<?php

namespace App\Models;

//use Illuminate\Support\Facades\Config;

/**
 * Add this trait to your User model so
 * that you can retrieve the beats for a user.
 */
trait Producer
{
    /**
     * Returns all beats that this user has made.
     */
    public function beats()
    {
        return $this->morphMany(Config::get('beats.model'), 'producer');
    }
}
