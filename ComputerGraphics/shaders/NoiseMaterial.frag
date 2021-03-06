// texture coordinates (incoming data from vertex shader)
in vec2 UV;

// also need time data to generate animated effects
uniform float time;
// control amount of distortion with extra factor [0 --- ???]
uniform float noiseLevel;
// control speed of distortion with extra factor
uniform float noiseSpeed;

// final color sent to GPU
out vec4 fragColor;

// create psuedo random numbers in range [0 ... 1]
float random(vec2 UV)
{
    return fract( 73.1024 * sin( 9.2468 * UV.x + 5.42 * UV.y ) );	
}


float boxRandom(vec2 UV, float scale)
{
    // vec2 integers in range [0 ... scale]
    vec2 iUV = floor(UV * scale);
    return random(iUV);
}

// average four pixel colors at corners of square containing this point
float smoothRandom(vec2 UV, float scale)
{
    vec2 iUV0 = floor(UV * scale);
    vec2 iUV1 = iUV0 + vec2(1.0, 0.0);
    vec2 iUV2 = iUV0 + vec2(0.0, 1.0);
    vec2 iUV3 = iUV0 + vec2(1.0, 1.0);

    // values at each point:
    float R0 = random(iUV0);
    float R1 = random(iUV1);
    float R2 = random(iUV2);
    float R3 = random(iUV3);

    // calculate averages of random values:
    vec2 p = fract(UV * scale); // calculate weights in the weighted average

    // replace staight lines from 0 to 1 with a smooth curve from 0 to 1.
    p = smoothstep(0.0, 1.0, p);

    float R01 = mix( R0, R1, p.x );
    float R23 = mix( R2, R3, p.x );
    return mix( R01, R23, p.y );
}

float fractalRandom(vec2 UV, float baseScale)
{
    float r = smoothRandom(UV, 1.0 * baseScale) / 2.0;
    r = r +   smoothRandom(UV, 2.0 * baseScale) / 4.0;
    r = r +   smoothRandom(UV, 4.0 * baseScale) / 8.0;
    r = r +   smoothRandom(UV, 8.0 * baseScale) / 16.0;
    return r;
}

vec3 fractalRandom3(vec2 UV, float baseScale)
{
    float r = fractalRandom(UV, baseScale);
    float g = fractalRandom(UV + vec2(1.337, 4.268), baseScale);
    float b = fractalRandom(UV + vec2(9.123, 2.007), baseScale);
    return vec3(r, g, b);
}


void main()
{

    // float r = random(UV);

    // float r = boxRandom(UV, 8.0);

    // float r = smoothRandom(UV, 8.0);

    // float r = fractalRandom(UV, 10.0);

    // display random gray noise
    // fragColor = vec4(r, r, r, 1);
   
    // display random rainbow noise texture
    // vec3 rgb = fractalRandom3(UV, 5.0);
    // fragColor = vec4( rgb, 1 );

    // create a cloud/sky texture  
    // vec3 white = vec3(1, 1, 1);
    // vec3 blue  = vec3(0.2, 0.5, 0.8); // sky blue
    // float r = fractalRandom(UV + vec2(time, 0) / 10.0, 5.0); // drifting clouds
    // vec3 rgb = mix( white, blue, r );
    // fragColor = vec4( rgb, 1 );
    
    /*
    // create lava texture
    vec3 red = vec3(0.5, 0, 0);
    vec3 yellow = vec3(1, 1, 0);
    // change UV coordinates
    vec3 noise = fractalRandom3(UV, 5.0);
    float t = time / 10.0;
    vec3 noiseTime = vec3( noise.r * sin(t) + noise.g * cos(t),
                           noise.g * sin(t) + noise.b * cos(t),
                           noise.b * sin(t) + noise.r * cos(t)  );

    vec2 noiseUV = UV + 1.0 * vec2(noiseTime.r, noiseTime.g);

    float r = fractalRandom(noiseUV, 10.0);
    vec3 rgb = mix( red, yellow, r );
    fragColor = vec4( rgb, 1 );
    */

    /*
    // create wood texture
    vec3 tan   = vec3(0.5, 0.4, 0.3);
    vec3 brown = vec3(0.3, 0.2, 0.1);
    float t = fractalRandom(UV, 2.0);
    // range of r = [-1, 1]
    float r = sin(200.0 * (UV.y + t/8.0));
    vec3 rgb = mix( tan, brown, r );
    fragColor = vec4( rgb, 1 );
    */

    // create marble texture
    vec3 green = vec3(0, 0.2, 0);
    vec3 white = vec3(1, 1, 1);
    float t = fractalRandom(UV, 2.0);
    float r = sin(20.0 * (UV.y + 2.0 * t));
    if (r > 0)
        r = pow(r, 60);    
    vec3 rgb = mix( white, green, r );

    // secondary marble layer
    vec3 green2 = vec3(0.2, 0.4, 0.2);
    vec3 white2 = vec3(0.8, 0.8, 0.8);
    float t2 = fractalRandom(UV, 16.0);
    float r2 = sin(25.0 * (UV.y + 2.0 * t2));
    if (r2 > 0)
        r2 = pow(r2, 60);    
    vec3 rgb2 = mix( white2, green2, r2 );

    fragColor = vec4( mix(rgb, rgb2, 0.5), 1 );

}