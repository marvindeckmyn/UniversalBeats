<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Beat extends Model
{
    use HasFactory;
    public $timestamps = false;

    protected $hidden = [
        'producer_type',
    ];

    protected $with = [
        'producer'
    ];

    public function producer() {
        return $this->morphTo();
    }

    public function user() {
        return $this->belongsTo('App\Models\User');
    }
}
