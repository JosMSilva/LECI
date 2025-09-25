library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;


entity AddSub4 is

	port(a,b : in std_logic_vector(3 downto 0);
			sub: in std_logic;
			sum: out std_logic_vector(3 downto 0);
			cOut: out std_logic);
			
end AddSub4;

architecture Behavioral of AddSub4 is

	signal s_a, s_b, s_s : unsigned(4 downto 0);
	
begin
	s_a <= '0' & unsigned(a); -- '0's para capturar o cout do
	s_b <= '0' & unsigned(b); -- do bit mais significativo
	s_s <= (s_a + s_b) when (sub = '0') else (s_a - s_b);
	sum <= std_logic_vector(s_s(3 downto 0));
	cOut <= s_s(4);
	
end Behavioral;
