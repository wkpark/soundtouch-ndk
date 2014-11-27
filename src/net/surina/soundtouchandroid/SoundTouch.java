/**
 * Copyright 2013 Steve Myers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package net.surina.soundtouchandroid;

public class SoundTouch {
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    static {
        System.loadLibrary("soundtouch_jni");
    }

    private int channels, samplingRate, bytesPerSample;
    private float tempo;
    private float pitchSemi;
    private long track;

    public SoundTouch(int channels, int samplingRate,
            int bytesPerSample, float tempo, float pitchSemi)
    {

        this.channels = channels;
        this.samplingRate = samplingRate;
        this.bytesPerSample = bytesPerSample;
        this.tempo = tempo;
        this.pitchSemi = pitchSemi;

        this.track = setup(channels, samplingRate, bytesPerSample, tempo, pitchSemi);
    }

    public int getBytesPerSample()
    {
        return bytesPerSample;
    }

    public int getChannels()
    {
        return channels;
    }

    public long getOutputBufferSize()
    {
        return getOutputBufferSize(track);
    }

    public long availableBytes()
    {
        return getOutputBufferSize(track);
    }

    public int getSamplingRate()
    {
        return samplingRate;
    }

    public float getPitchSemi()
    {
        return pitchSemi;
    }

    public float getTempo()
    {
        return tempo;
    }

    public long getTrackId()
    {
        return track;
    }

    public void setBytesPerSample(int bytesPerSample)
    {
        this.bytesPerSample = bytesPerSample;
    }

    public void setChannels(int channels)
    {
        this.channels = channels;
    }

    public void setSamplingRate(int samplingRate)
    {
        this.samplingRate = samplingRate;
    }

    public void setSpeech(boolean isSpeech)
    {
        setSpeech(track, isSpeech);
    }

    public void setPitchSemi(float pitchSemi)
    {
        this.pitchSemi = pitchSemi;
        setPitchSemi(track, pitchSemi);
    }

    public void setPitch(float pitch)
    {
        setPitchSemi(track, pitch);
    }

    public void setTempo(float tempo)
    {
        this.tempo = tempo;
        setTempo(track, tempo);
    }

    public void setSpeed(float speed)
    {
        setTempo(speed);
    }

    public void setRate(float rate)
    {
        setRate(track, rate);
        return;
    }

    public float setTempoChange(float tempoChange)
    {
        if (tempoChange < -50)
            tempoChange = -50;
        else if (tempoChange > 100)
            tempoChange = 100;
        this.tempo = 1.0f + 0.01f * tempoChange;
        setTempoChange(track, tempoChange);

        return tempoChange;
    }

    public void clearBuffer()
    {
        clearBytes(track);
    }

    public void flush()
    {
        clearBytes(track);
    }

    public void putBytes(byte[] input, int length)
    {
        putBytes(track, input, length);
    }

    public int getBytes(byte[] output, int length)
    {
        return getBytes(track, output, length);
    }

    // call finish after the last bytes have been written
    public void finish()
    {
        finish(track, DEFAULT_BUFFER_SIZE);
    }

    private static synchronized native final void clearBytes(long track);

    private static synchronized native final void finish(long track, int bufSize);

    private static synchronized native final int getBytes(long track,
            byte[] output, int toGet);

    private static synchronized native final void putBytes(long track,
            byte[] input, int length);

    private static synchronized native final long setup(
            int channels, int samplingRate, int bytesPerSample, float tempo,
            float pitchSemi);

    private static synchronized native final void setPitchSemi(long track,
            float pitchSemi);

    private static synchronized native final long getOutputBufferSize(long track);

    private static synchronized native final void setSpeech(long track,
            boolean isSpeech);

    private static synchronized native final void setTempo(long track,
            float tempo);

    private static synchronized native final void setTempoChange(long track,
            float tempoChange);

    private static synchronized native final void setRate(long track,
            float rate);
}
