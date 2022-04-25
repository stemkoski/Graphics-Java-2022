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
    return fract( 73.1024 * sin( 90.2468 * UV.x + 53.42 * UV.y ) );	
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



void main()
{

    // float r = random(UV);

    // float r = boxRandom(UV, 8.0);

    float r = smoothRandom(UV, 64.0);

    fragColor = vec4(r, r, r, 1); // some gray color
   
}