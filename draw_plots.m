#! /usr/bin/env octave -q
1;

%{ 
Author: Dan-George Filimon

This file is part of SortingBenchmarks.

SortingBenchmarks is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/ ...
        licenses/>.
%}

% be careful, textread is in the octave-io package!

function drawPlots(prefix)
  colors = 'rgbmck';  
  files = dir([prefix, '.*.benchmark']); 
  
  clf;
  hold on;

  for i = 1 : length(files)
    printf('%s %s\n', files(i).name(), colors(i));
    [x, y] = textread(files(i).name, '%d', 2);
    %p = polyfit(x, y, 2);    
    %plot(x, polyval(p, x), colors(i), 'LineWidth', 1)
    plot(x, y, [colors(i), 'x'], 'MarkerSize', 2);

    xlabel('Number of elements');
    ylabel('Nanoseconds');
  end
  title([prefix, ' benchmarks']);
  legend(files.name)
  print([prefix, '.benchmarks.png'], '-dpng');

  hold off;
  pause();
end

# plotting benchmarks with random data
drawPlots('rand')

# plotting benchmarks with worstcase data
drawPlots('worstcase')
  