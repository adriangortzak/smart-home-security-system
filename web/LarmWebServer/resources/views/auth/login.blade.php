<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/login-style.css">
    </head>
    <body>
      <form class="login" role="form" method="POST" action="{{ url('/login') }}">
                        {!! csrf_field() !!}

    <p>

      <label for="login">Email:</label>
    <input type="email"  name="email" placeholder="exempel@gmail.com"  value="{{ old('email') }}">
    </p>

    <p>

      <label for="password">Password:</label>


  <input type="password"  name="password" placeholder="**********">

    </p>

    <p class="login-submit">

      <button type="submit"  class="login-button">Login</button>

    </p>
    <p class="forgot-password"><a href="{{ url('/password/reset') }}">Forgot your password?</a></p>
             <ul class="help-block">

               @if ($errors->has('email'))
                                 
                                        <li>{{ $errors->first('email') }}</li>
                                    
                                @endif
          
                                @if ($errors->has('password'))
                             
                                        <li>{{ $errors->first('password') }}</li>
                                @endif
             </ul>
                                
  </form>
</body>
</html>

