@extends('master')

@section('history_active')
        active
@stop


@section('main_content')
<h1>Full History</h1>
{{  getAllMessageFromHistory() }}
@stop
