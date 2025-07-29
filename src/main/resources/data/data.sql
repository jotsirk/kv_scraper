INSERT INTO property_ticks (origin, property_key, url)
  VALUES
    ('KV', '3757253', 'https://www.kv.ee/3757253'),
    ('KV', '3761282', 'https://www.kv.ee/3761282'),
    ('KV', '3768133', 'https://www.kv.ee/3768133'),
    ('KV', '3760216', 'https://www.kv.ee/3760216'),
    ON CONFLICT (property_key) DO NOTHING;
