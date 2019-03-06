require 'yard'
YARD::Rake::YardocTask.new(:yard) do |t|
t.files = [
    'readme_index.md',
    'features/specifications/*.feature',
    'features/specifications/*/*.feature',
    'features/specifications/*/*/*.feature',
    'features/specifications/*/*/*/*.feature',
    'features/specifications/*/*/*/*.feature']
    t.options = %w(--markup-provider=redcarpet --markup=markdown --main=readme_index.md)
end