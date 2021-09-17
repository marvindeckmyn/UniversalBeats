<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Play extends Model
{
    protected $fillable = ['beat_id'];

    public function user() {
        return $this->belongsTo('App\Models\User');
    }
}
