<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class triggers extends Model
{
    protected $fillable = [
        'name', 'type', 'sensor',
    ];
}
