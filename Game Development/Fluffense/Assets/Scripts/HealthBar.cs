using System;
using UnityEngine;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class HealthBar : MonoBehaviour
{
    public Slider slider;

    public Image fill;

    public Gradient gradient;

    public UnityEngine.UI.Outline outline;
    // Start is called before the first frame update

    public float damageEffectDuration = 0.5f;
    private float damageEffectTimer = -1f;

    private float health = 100;

    private Color effectColor;

    private void Start()
    {
        fill.color = gradient.Evaluate(1f);
        outline = GetComponentInChildren<UnityEngine.UI.Outline>();
        outline.enabled = false;
        effectColor = outline.effectColor;
        Color c = effectColor;
        outline.effectColor = new Color(c.r, c.g, c.b, 255);
    }

    public void SetHealth(float health, float maxHealh)
    {
        float p = health / maxHealh;
        if (p < slider.value) damageEffectTimer = damageEffectDuration;
        slider.value = p;
        fill.color = gradient.Evaluate(p);
    }

    private void Update()
    {
     //   if(Input.GetMouseButtonDown(1)) SetHealth(health-=10,100);
        if(damageEffectTimer < 0) return;
        damageEffectTimer -= Time.deltaTime;
        float a = damageEffectTimer / damageEffectDuration;
        outline.enabled = true;
        if (a < 0)
        {
            a = 0;
            outline.enabled = false;
        }
        Color c = effectColor;
        outline.effectColor = new Color(c.r, c.g, c.b, a);
    }
}
